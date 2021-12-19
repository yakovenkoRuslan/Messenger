import * as React from 'react';
import {Button, View, Text } from 'react-native';


export function SettingsScreen(props) {
    const { signOut } = React.useContext(props.auth);
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text>Details Screen</Text>
        <Button
          title="SignOut"
          onPress={() => signOut()}
        />
      </View>
    );
  }