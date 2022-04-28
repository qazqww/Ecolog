import React from 'react';
import {Text, TextInput, View, StyleSheet} from 'react-native';
import CommunityTab from '../components/User/Community/CommunityTab';
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#5FA2E5',
  },
  topMenu: {
    width: '100%',
    color: '#ffffff',
    padding: 20,
  },
  topInput: {
    backgroundColor: 'white',
    width: '100%',
    borderRadius: 5,
  },
  topTitle: {
    color: '#ffffff',
    fontWeight: '700',
    alignSelf: 'flex-start',
    marginBottom: 10,
  },
});

function CommunityScreen() {
  return (
    <View style={styles.container}>
      <View style={styles.topMenu}>
        <Text style={styles.topTitle}>커뮤니티</Text>
        <TextInput style={styles.topInput} />
      </View>
      <CommunityTab />
    </View>
  );
}

export default CommunityScreen;
