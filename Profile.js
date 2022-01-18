import * as React from 'react';
import { getUserInfo } from './UserStore';
import {FlatList, View, Text, StyleSheet} from 'react-native'
import axios from 'axios';


const styles = StyleSheet.create({
  container: {
   flex: 1,
   paddingTop: 22
  },
  item: {
    padding: 10,
    fontSize: 18,
    height: 44,
  },
});


export const Profile = () => {
  const [username, setUsername] = React.useState('');
  const [email, setEmail] = React.useState('');
  React.useEffect(() => {
    console.log(global.IP),
    getUserInfo('userToken').then(token=>{
    getUserInfo('userName').then(name=>
      axios.get("http://"+global.IP+":8080/profile?username="+name, {
        headers:{
          Authorization: 'Bearer_' + token
        }})).then(data=>{
          console.log(data.data.email),
        setEmail(data.data.email),
        setUsername(data.data.username)
      }
      
    )
  })
    }, []);
  return (
    <View style={styles.container}>
      
        <Text style={styles.item}>Username: {username}</Text>
        <Text style={styles.item}>Email: {email}</Text>
      
    </View>
  );
}