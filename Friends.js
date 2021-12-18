import * as React from 'react';
import {useState} from 'react';
import {Modal,TextInput, FlatList, Button, View, Text,StyleSheet } from 'react-native';

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
              <Text style={modal_styles.modalText}>Send message to {props.name}</Text>
              <TextInput style={{borderWidth:0.3}}/>
              <Button title="Dismiss"
                style={[modal_styles.button, modal_styles.buttonClose]}
                onPress={() => setModalVisible(!modalVisible)}/>
              <Button title="Send"
              style={[modal_styles.button, modal_styles.buttonClose]}
              onPress={() => (
                //Send
                setModalVisible(!modalVisible))}/>
            </View>
          </View>
        </Modal>
        <Button title={props.name}
          style={[modal_styles.button, modal_styles.buttonOpen]}
          onPress={() => setModalVisible(true)}/>
      </View>
    );
  }
  
  export const FriendsScreen = ({navigation})=> {
    return (
      <View>
        <FlatList
          data={[
            {key: 'Devin'},
            {key: 'Dan'},
            {key: 'Dominic'},
            {key: 'Jackson'},
            {key: 'James'},
            {key: 'Joel'},
            {key: 'John'},
            {key: 'Jillian'},
            {key: 'Jimmy'},
            {key: 'Julie'},
          ]}
          renderItem={({item}) => (
            <FriendComponent name={item.key}/>
          )}
        />
      </View>
    );
  }

  
const modal_styles = StyleSheet.create({
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
