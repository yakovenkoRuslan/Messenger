import * as React from 'react';
import {useState} from 'react';
import {View, Text,StyleSheet } from 'react-native';
import {Dropdown} from 'react-native-element-dropdown';



const pages = [{page: "Home"}, 
  {page:"Profile"}, 
  {page:"Friends"}, 
  {page:"Settings"}]



export function HomeScreen({ navigation }) {
  const [dropdown, setDropdown] = useState(null);
  const [selected, setSelected] = useState([]);
 
  const _renderItem = item => {
    return (
        <View style={styles.item}>
            <Text style={styles.textItem}>{item.page}</Text>
        </View>
      );
    };

  return (

     <Dropdown
        style={styles.dropdown}
        data={pages}
        // labelField="label"
         //valueField="value"
        // placeholder="Select item"
        // value="Fuck you"
        onChange={item => {
          //setDropdown(item.value);
          //setDropdown("Fuckyou"),
          console.log('selected', item);
          navigation.navigate(item.page)
        }}
         renderLeftIcon={() => (
             <Text>Fuck: </Text> 
         )}
        renderItem={item => _renderItem(item)}
    ></Dropdown>
    
  );
}

const styles = StyleSheet.create({
  FriendInfo: {
    alignContent: 'center',
    justifyContent: 'center',
    backgroundColor: '#ecf0f1',
    padding: 8
  },
        // container: {
        //     flex: 1,
        //     backgroundColor: 'white',
        //     padding: 40,
        // },
        dropdown: {
            borderWidth:0.3,
            //alignSelf:'left',
            // backgroundColor: 'white',
            // borderBottomColor: 'gray',
            // borderBottomWidth: 0.5,
             marginTop: 20,
        },
        item: {
            borderWidth:0.3,
            padding:'100%',
            paddingVertical: 17,
            paddingHorizontal: 4,
            flexDirection: 'row',
            justifyContent: 'space-between',
            alignItems: 'center',
        },
        textItem: {
            
            flex: 1,
            fontSize: 16,
        },
    });
