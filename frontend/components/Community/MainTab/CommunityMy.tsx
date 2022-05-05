import React from 'react';
import {StyleSheet, FlatList} from 'react-native';
import CommunityMyItem from './CommunityMyItem';
import {useQuery} from 'react-query';
import {getCommunityList} from '../../../api/community';
const styles = StyleSheet.create({
  myListContainer: {
    flexGrow: 0,
    width: '100%',
    minHeight: '100%',
    backgroundColor: '#ffffff',
    padding: 20,
  },
  myItem: {
    height: 150,
    backgroundColor: 'grey',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 20,
    borderRadius: 15,
  },
});

function CommunityMy({navigation}: any) {
  const {data: communityListData} = useQuery('CommunityList', getCommunityList);
  return (
    <FlatList
      style={styles.myListContainer}
      data={communityListData}
      renderItem={({item}: any) => (
        <CommunityMyItem navigation={navigation} community={item} />
      )}
    />
  );
}

export default CommunityMy;
