import React from 'react';
import {View, Text, StyleSheet} from 'react-native';

const styles = StyleSheet.create({
  Container: {
    flexGrow: 0,
    width: '100%',
    backgroundColor: '#ffffff',
    padding: 20,
  },
});

function CommunityHome() {
  return (
    <View style={styles.Container}>
      <Text>홈</Text>
    </View>
  );
}

export default CommunityHome;
