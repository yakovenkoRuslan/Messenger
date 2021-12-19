import * as React from 'react';
import {View, Text, StyleSheet, Button, TextInput, FlatList} from 'react-native'

import { useFocusEffect } from '@react-navigation/native';
import { removeUserInfo, getUserInfo } from './UserStore';


export function ChatScreen(){
    const [message, setMessage] = React.useState('');
    useFocusEffect(
        React.useCallback(() => {
         // alert('Screen was focused');
          return () => {
            //alert('Screen was unfocused'),
            removeUserInfo('chatUsername')
          };
        }, [])
      );

    const UpdateMessages = ()=>{}
    const SendMessage = (toUser, message)=>{}

    const INTERVAL_MS = 5000;

    React.useEffect(() => {
        const interval = setInterval(() => {
            UpdateMessages()
          
        }, INTERVAL_MS);

        return () => clearInterval(interval); 
    }, [])

    return(
        <View style={styles.container}>
        
        <View style={styles.bottom}>
        <TextInput style={styles.input}
        placeholder='Your message'
         value={message}
         onChangeText={setMessage}
        />
          <Button title="Send" onPress={()=>{
            getUserInfo('chatUsername').then(chatUserName=>{
                SendMessage(chatUserName, message),
                setMessage('')
            })

          }}/>
        </View>
      </View>
    )
}

const styles = StyleSheet.create({
    container: {
      flex: 1
    },
    heading: 
      {
        marginTop: 40
      
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
    }
  })