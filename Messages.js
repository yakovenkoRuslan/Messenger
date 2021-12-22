import * as React from 'react';
import {View, Text, StyleSheet, Button, TextInput, FlatList} from 'react-native'

import { useFocusEffect } from '@react-navigation/native';
import { removeUserInfo, getUserInfo } from './UserStore';
import axios from 'axios';

export function ChatScreen(){
    const [message, setMessage] = React.useState('');
    const [messagesList, setMessagesList] = React.useState([]);
    useFocusEffect(
        React.useCallback(() => {
         // alert('Screen was focused');
         UpdateMessages()
          return () => {
            //alert('Screen was unfocused'),
            removeUserInfo('chatUsername')
          };
        }, [])
      );

    const UpdateMessages = ()=>{
        getUserInfo('userToken').then(token=>{
            getUserInfo('chatUsername').then(chatUserName=>{
                getUserInfo('userName').then(name=>{
                    axios.get("http://"+global.IP+":8080/message?firstUser="+name+"&secondUser="+chatUserName,
                    {
                        headers:{
                            Authorization: 'Bearer_' + token
                        }
                    }
                    )
                    .then(data=>{
                        setMessagesList(data.data)})
                       
                })
            })
        })

    }
    const SendMessage = (toUser, message)=>{
        getUserInfo('userToken').then(token=>{
            getUserInfo('chatUsername').then(chatUserName=>{
                getUserInfo('userName').then(name=>{
                    axios.post("http://"+global.IP+":8080/message",{
                        sender:name,
                        recipient:chatUserName,
                        msg:message
                    },
                    {
                        headers:{
                            Authorization: 'Bearer_' + token
                        }
                    }
                    )
                    .then(data=>console.log(data.data))
                })
            })
        })
    }

    const INTERVAL_MS = 5000;

    const [username, setUsername] = React.useState('');
    React.useEffect(() => {
        getUserInfo('userName').then(name=>setUsername(name))
        const interval = setInterval(() => {
           UpdateMessages()
          
        }, INTERVAL_MS);

        return () => clearInterval(interval); 
    }, [])

    return(
        <View style={styles.container}>
            <View style={styles.top}>
        <FlatList nestedScrollEnabled  
          data={messagesList}
          renderItem={({item}) => (
                username == item.sender? 
                (<View style={[styles.messageBox,{flexDirection: 'row', justifyContent: 'flex-start'}]}>
                    <Text style={[styles.input,styles.message]}> {item.msg}</Text>
                </View>)
                :
                (<View style={[styles.messageBox,{flexDirection: 'row', justifyContent: 'flex-end'}]}>
                    <Text style={[styles.input,styles.message]}> {item.msg}</Text>
                </View>)
  
       )}
        />

</View>


<View style={styles.bottom}>
                <TextInput style={styles.input}
                    placeholder='Your message'
                    value={message}
                    onChangeText={setMessage}/>
                <Button title="Send" onPress={()=>{
                    getUserInfo('chatUsername').then(chatUserName=>{
                        SendMessage(chatUserName, message),
                        setMessage(''),
                        UpdateMessages()

                    })
                }}/>
            </View>
      </View>
    )
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      paddingTop: 22
    },
    heading: 
      {
        marginTop: 40
      
    },
    message:{
        
        justifyContent:'center',
        fontWeight: "bold",
        color:"purple",
        height: 50,
    }, 
    messageBox:{
        
        color: 'blue',
        borderWidth:0.3
    },  
    input: {
        
        // flex: 5,
        // justifyContent: 'flex-end',
        marginHorizontal: 8,
        marginVertical: 10,
        borderWidth:4,
        height: 70,
        backgroundColor: '#ffffff',
        paddingLeft: 15,
        paddingRight: 15
      },
    text: 
     {
        marginHorizontal: 8,
        marginVertical: 10
      
    },
    bottom: {
      flex: 1,
      justifyContent: 'flex-end',
      marginBottom: 36
    },
    top: {
        flex: 1,
        justifyContent: 'flex-start'
      }
  })