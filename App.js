
import * as React from 'react';
import { Alert, Button, Text, TextInput, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import {HomeScreen} from "./Home"
import { SettingsScreen} from './Setting';
import { FriendsScreen } from './Friends';
import { Profile } from './Profile';
import * as SecureStore from 'expo-secure-store';
import axios from 'axios';

const AuthContext = React.createContext();

function SplashScreen() {
  return (
    <View>
      <Text>Loading...</Text>
    </View>
  );
}

function SignInScreen({navigation}) {
  const [username, setUsername] = React.useState('');
  const [password, setPassword] = React.useState('');

  const { signIn } = React.useContext(AuthContext);

  return (
    <View>
      <TextInput
        placeholder="Username"
        value={username}
        onChangeText={setUsername}
      />
      <TextInput
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />
      <Button title="Sign in" onPress={() => signIn({ username: username, password: password })} />
      <Button title="Sign up screen" onPress={() => navigation.navigate("SignUp")} style={{
          flex:1,
          flexDirection:'row',
          alignItems:'center',
          justifyContent:'center'
      }}
      />
    </View>
  );
}

function SignUpScreen({navigation}) {
  const [username, setUsername] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [email, setEmail] = React.useState('');
  const { signUp } = React.useContext(AuthContext);

  return (
    <View>
      <TextInput
        placeholder="Username"
        value={username}
        onChangeText={setUsername}
      />
      <TextInput
        placeholder="email"
        value={email}
        onChangeText={setEmail}
      />
      <TextInput
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />
      <Button title="Sign up" onPress={() => signUp({ username: username, email: email, password: password,
      nav: navigation })} />
    </View>
  );
}

async function save(key, value) {
  await SecureStore.setItemAsync(key, value);
}

const Stack = createStackNavigator();

export default function App({ navigation }) {
  const [state, dispatch] = React.useReducer(
    (prevState, action) => {
      switch (action.type) {
        case 'RESTORE_TOKEN':
          return {
            ...prevState,
            userToken: action.token,
            isLoading: false,
          };
        case 'SIGN_IN':
          return {
            ...prevState,
            isSignout: false,
            userToken: action.token,
          };
        case 'SIGN_OUT':
          return {
            ...prevState,
            isSignout: true,
            userToken: null,
          };
      }
    },
    {
      isLoading: true,
      isSignout: false,
      userToken: null,
    }
  );

  React.useEffect(() => {
    // Fetch the token from storage then navigate to our appropriate place
    const bootstrapAsync = async () => {
      let userToken;

      try {
        // Restore token stored in `SecureStore` or any other encrypted storage
         //userToken = await SecureStore.getItemAsync('userToken');
      } catch (e) {
        // Restoring token failed
      }

      // After restoring token, we may need to validate it in production apps

      // This will switch to the App screen or Auth screen and this loading
      // screen will be unmounted and thrown away.
      dispatch({ type: 'RESTORE_TOKEN', token: userToken });
    };

    bootstrapAsync();
  }, []);

  const authContext = React.useMemo(
    () => ({
      signIn: async (dataIn) => {
        console.log(dataIn);
        axios.post("http://192.168.0.101:8080/login", {
          username: dataIn.username,
          password: dataIn.password
        }).then(res=>{
          console.log(res.data),
          //save('userToken', data.data.autenticationToken),
          dispatch({ type: 'SIGN_IN', token: res.data.authenticationToken})
        }).catch(error => console.log(error));
        
        // } //finally {
        //   setLoading(false);
        // }
        // In a production app, we need to send some data (usually username, password) to server and get a token
        // We will also need to handle errors if sign in failed
        // After getting token, we need to persist the token using `SecureStore` or any other encrypted storage
        // In the example, we'll use a dummy token

       
      },
      signOut: () => dispatch({ type: 'SIGN_OUT' }),
      signUp: async (dataIn) => {

        console.log(dataIn);
        axios.post("http://192.168.0.101:8080/registration", {
          username: dataIn.username,
          email:dataIn.email,
          password: dataIn.password
        }).then(data=>{
          console.log(data),
          dataIn.nav.navigate("SignIn")
          //save('userToken', data.data.autenticationToken),
           //dispatch({ type: 'SIGN_IN', token: data.data.authenticationToken})
        }).catch(error => {console.log(error),
          Alert.alert('Sign up', 'Invalid sing up data')});

        
        // In a production app, we need to send user data to server and get a token
        // We will also need to handle errors if sign up failed
        // After getting token, we need to persist the token using `SecureStore` or any other encrypted storage
        // In the example, we'll use a dummy token

        
      },
    }),
    []
  );

  return (
  <AuthContext.Provider value={authContext}>
      <NavigationContainer>
        <Stack.Navigator>
          {state.isLoading ? (
            // We haven't finished checking for the token yet
            <Stack.Screen name="Splash" component={SplashScreen} />
          ) : state.userToken == null ? (
            <>
            <Stack.Screen
              name="SignIn"
              component={SignInScreen}
              options={{
                title: 'Sign in',
                // When logging out, a pop animation feels intuitive
                animationTypeForReplace: state.isSignout ? 'pop' : 'push',
              }}
            />

            <Stack.Screen name="SignUp" component={SignUpScreen}/>
            </>
          ) : (
           <>
            <Stack.Screen name="Home" component={HomeScreen} />
            <Stack.Screen name="Profile" component={Profile} />
            <Stack.Screen name="Friends"  component = {FriendsScreen}/>
            <Stack.Screen name="Settings" component = {SettingsScreen} /> 
            </>
          )}
        </Stack.Navigator>
      </NavigationContainer> 
    </AuthContext.Provider>
  );
}