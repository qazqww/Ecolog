import React from 'react';
import {View, Text, StyleSheet} from 'react-native';

const styles = StyleSheet.create({
  Container: {
    flexGrow: 0,
    width: '100%',
    backgroundColor: '#ffffff',
    padding: 20,
    height: '100%',
  },
  contentContainer: {
    borderColor: '#000000',
    borderWidth: 1,
  },
});

function CommunityFree() {
  return (
    <View style={styles.Container}>
      <Text>자유 게시판</Text>
      <View style={styles.contentContainer}>
        <Text>1</Text>
        <Text>2</Text>
        <Text>3</Text>
        <Text>4</Text>
        <Text>5</Text>
      </View>
    </View>
  );
}

export default CommunityFree;
