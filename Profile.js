import * as React from 'react';

import {FlatList, View, Text, StyleSheet} from 'react-native'

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
  return (
    <View style={styles.container}>
      <FlatList
        data={[ 
          {key: 'Username: '},
          {key: 'Name, Surname: '},
          {key: "Stat: "},
          {key: "Winrate: "},
        ]}
        renderItem={({item}) => <Text style={styles.item}>{item.key}</Text>}
      />
    </View>
  );
}