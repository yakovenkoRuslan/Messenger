import * as React from 'react';
import {Button, View, Text } from 'react-native';
import axios from 'axios';
import { getUserInfo } from './UserStore';

export function SettingsScreen(props) {
    const { signOut } = React.useContext(props.auth);
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text>Details Screen</Text>
        <Button
          title="SignOut"
          onPress={() => {
            getUserInfo('userToken').then(token=>{
            getUserInfo('userName').then(name=>{
                axios.put("http://"+global.IP+":8080/settings",{
                  username:name,
                  isOnline:false,
                }, {
                  headers:{
                      Authorization: 'Bearer_' + token
                  }
              }) 
            })
          })
          
            signOut()}}
        />
      </View>
    );
  }