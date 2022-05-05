import React from 'react';
import {Text, View, TouchableOpacity, StyleSheet, Image} from 'react-native';
import {Community} from '../../../api/community';
const styles = StyleSheet.create({
  myItem: {
    height: 150,
    backgroundColor: 'grey',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 20,
    borderRadius: 15,
  },
  image: {
    height: '30%',
  },
  mimage: {
    height: '20%',
    width: '10%',
  },
});
interface CommunityItemProps {
  navigation: any;
  community: Community;
}
function CommunityMyItem({navigation, community}: CommunityItemProps) {
  return (
    <TouchableOpacity onPress={() => navigation.navigate('CommunityHome')}>
      <View style={styles.myItem}>
        <Image source={{uri: community.image}} style={styles.image} />
        <Text>{community.title}</Text>
        <Text>{community.no}</Text>
        <Text>{community.manager.email}</Text>
        <Image source={{uri: community.manager.image}} style={styles.mimage} />
      </View>
    </TouchableOpacity>
  );
}

export default CommunityMyItem;
