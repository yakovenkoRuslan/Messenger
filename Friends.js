import * as React from 'react';
import {useState} from 'react';
import {Alert, Modal,TextInput, FlatList, Button, View, Text,StyleSheet } from 'react-native';
import { getUserInfo, saveUserInfo } from './UserStore';
import axios from 'axios';

function FriendComponent(props){
  const [modalVisible, setModalVisible] = useState(false);
    return (
      <View>
        <Modal onDismiss={()=>setModalVisible(false)}
          animationType="fade"
          transparent={true}
          visible={modalVisible}
          onRequestClose={() => {
            setModalVisible(!modalVisible);
          }}
        >
          <View >
            <View style={modal_styles.modalView}>
            <Text style={modal_styles.modalText}>Email: {props.email}</Text>
              <Text style={modal_styles.modalText}>Send message to {props.name}</Text>
              <Button title="Messages"
                style={[modal_styles.button, modal_styles.buttonClose]}
                onPress={() => {
                    saveUserInfo('chatUsername', props.name).then(
                      props.nav.navigate("Messages"),
                      setModalVisible(!modalVisible)
                    )
                  }}/>
              <Button title="Close"
              style={[modal_styles.button, modal_styles.buttonClose]}
              onPress={() => (
                //Send
                setModalVisible(!modalVisible))}/>
            </View>
          </View>
        </Modal>
        <Button title={props.name}
          style={[modal_styles.button, modal_styles.buttonOpen]}
          onPress={() => {
              //request for the profile settings
              setModalVisible(true)
            }}/>
      </View>
    );
  }
  
  const FriendsContext = React.createContext();

  export const FriendsScreen = ({navigation})=> {
    const [friendsList, setFriendsList] = useState([]);
    const [friendToAdd, setFriendToAdd] = useState('');

    const UpdateFriendsList = ()=>{
      getUserInfo('userToken').then(token=>{
        console.log("token : ")
        console.log(token);

        getUserInfo('userName').then(name=>{

        console.log(name);
        axios.get("http://"+global.IP+":8080/friends?user="+name,{
          headers:{
            Authorization: 'Bearer_' + token
          }
        }).then(data=>{
          console.log("data:"); 
          console.log(data.data);
          setFriendsList(data.data);
        }).catch(error=>console.log(error));
        })
      });

    }

     React.useEffect(() => {

      UpdateFriendsList();
      
      }, []);
    return (
      <FriendsContext.Provider value={FriendsContext}>
        <View>
          <FlatList
            data = {friendsList}
            renderItem={({item}) => (
              <FriendComponent email={item.email} name={item.username} nav = {navigation}/>
            )}
          />
          <Button title="Add friend" onPress={()=>{
            
            getUserInfo('userToken').then(token=>{
            console.log(friendToAdd);
            getUserInfo('userName').then(name=>{
              console.log(name);
            axios.post("http://"+global.IP+":8080/friends/add-friend",{
              firstUsername:name,
              secondUsername:friendToAdd
            }, {
              headers:{
                Authorization: 'Bearer_' + token
              }
            }
            ).catch(error=>{
              console.log(error)
            })
            UpdateFriendsList();
           } )

          });

          }
          } color="purple"/>
         
           <TextInput 
           placeholder='Friend to add username'
           value={friendToAdd} style={modal_styles.input} onChangeText={setFriendToAdd}/> 
        </View>
      </FriendsContext.Provider>
    );
  }


const modal_styles = StyleSheet.create({
  input: {
    borderWidth:4,
    height: 70,
    backgroundColor: '#ffffff',
    paddingLeft: 15,
    paddingRight: 15
  },
  

  centeredView: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    marginTop: 22
  },
  modalView: {
    margin: 20,
    backgroundColor: "white",
    borderRadius: 20,
    padding: 35,
    alignItems: "center",
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 2
    },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5
  },
  button: {
    borderRadius: 20,
    padding: 10,
    elevation: 2
  },
  buttonOpen: {
    backgroundColor: "#F194FF",
  },
  buttonClose: {
    backgroundColor: "#2196F3",
  },
  textStyle: {
    color: "white",
    fontWeight: "bold",
    textAlign: "center"
  },
  modalText: {
    marginBottom: 15,
    textAlign: "center"
  }
});
