
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Alert } from 'react-native';

export async function saveUserInfo(key, value) {
    try {
        const jsonValue = value
        await AsyncStorage.setItem(key, jsonValue)
      } catch (e) {
        Alert.alert("storage error", e);
      }
  }

export async function getUserInfo(key) {
    try {
        let value  = await AsyncStorage.getItem(key);
        return await value;
      } catch(e) {
        Alert.alert("storage error", e);
      }
      return null;
  }
  