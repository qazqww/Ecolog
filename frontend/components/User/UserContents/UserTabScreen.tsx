import React from 'react';
import {View, Text, useWindowDimensions, StyleSheet} from 'react-native';
import {TabView, SceneMap, TabBar} from 'react-native-tab-view';
// Components
import UserPlogging from './Plogging/UserPlogging';
import UserCampaign from './Campaign/UserCampaign';
import UserAvatar from './Avatar/UserAvatar';
import {PloggingList} from '../../../api/plogging';

// Style
const fontStyles = (size?: number, weight?: any) =>
  StyleSheet.create({
    buttonText: {
      fontSize: size || 15,
      fontWeight: weight || 'normal',
      color: '#FFFFFF',
    },
  });

interface UserTabProps {
  ploggingList: PloggingList | null;
}

function UserTab({ploggingList}: UserTabProps) {
  const layout = useWindowDimensions();
  const [index, setIndex] = React.useState(0);
  const [routes] = React.useState([
    {key: 'plogging', title: '플로깅'},
    {key: 'campaign', title: '캠페인'},
    {key: 'avatar', title: '아바타'},
  ]);

  // Tab-View
  const FirstRoute = () =>
    ploggingList ? (
      <UserPlogging ploggingList={ploggingList} />
    ) : (
      <View>
        <Text>로딩중</Text>
      </View>
    );
  const SecondRoute = () => <UserCampaign />;
  const ThirdRoute = () => <UserAvatar />;

  const renderScene = SceneMap({
    plogging: FirstRoute,
    campaign: SecondRoute,
    avatar: ThirdRoute,
  });

  return (
    <TabView
      navigationState={{index, routes}}
      renderScene={renderScene}
      onIndexChange={setIndex}
      initialLayout={{width: layout.width}}
      renderTabBar={props => (
        <TabBar
          {...props}
          indicatorStyle={{backgroundColor: '#FFF'}}
          style={{backgroundColor: '#5FA2E5'}}
          pressColor={'#548dc5'}
          renderLabel={({route}) => (
            <View>
              <Text style={fontStyles(18, '600').buttonText}>
                {route.title}
              </Text>
            </View>
          )}
        />
      )}
    />
  );
}

export default UserTab;
