import React, {useEffect, useState} from 'react';
import {Text, Image, View, TouchableOpacity, StyleSheet} from 'react-native';
// Components
import PloggingTopRanking from '../../components/Plogging/Ranking/PloggingTopRanking';
import PloggingBottomRanking from '../../components/Plogging/Ranking/PloggingBottomRanking';
import {useMutation} from 'react-query';
import {getTimeRanking, PloggingRankList} from '../../api/plogging';

const styles = (color?: any, marginT?: any, marginL?: any) =>
  StyleSheet.create({
    mainContainer: {
      marginVertical: 20,
      height: '100%',
    },
    imgContainer: {
      width: '100%',
      paddingHorizontal: 20,
      alignItems: 'center',
    },
    headerImg: {
      width: '100%',
      aspectRatio: 2.5,
      marginBottom: 20,
      borderRadius: 10,
    },
    btnContainer: {
      flexDirection: 'row',
      marginTop: marginT || 0,
      paddingHorizontal: 20,
    },
    btnItem: {
      width: 50,
      height: 25,
      justifyContent: 'center',
      alignItems: 'center',
      marginLeft: marginL || 0,
      backgroundColor: color || '#d4d4d4',
      borderRadius: 10,
    },
  });

const fontStyles = (size?: any, weight?: any, color?: any) =>
  StyleSheet.create({
    rankText: {
      fontSize: size || 15,
      fontWeight: weight || 'normal',
      color: color || '#000',
    },
  });

function PloggingRankingScreen() {
  const [rankData, setRankData] = useState<PloggingRankList>([]);
  const [periodType, setPeriodType] = useState<string>('all');
  const {mutate: getTimeRank, isLoading} = useMutation(getTimeRanking, {
    onSuccess: data => {
      setRankData(data);
    },
    onError: error => {
      console.error(error);
    },
  });
  const topRankData = [...rankData].slice(0, 3);
  const bottomRankData = [...rankData].slice(3);

  useEffect(() => {
    getTimeRank(periodType);
  }, [periodType]);

  return (
    <View style={styles().mainContainer}>
      <View style={styles().imgContainer}>
        <Image
          style={styles().headerImg}
          source={{
            uri: 'https://cdn.itbiznews.com/news/photo/202111/55365_50621_652.png',
          }}
        />
      </View>

      <View style={styles().btnContainer}>
        <Text style={fontStyles(18, '600').rankText}>플로거 랭킹</Text>
        <TouchableOpacity style={styles(null, null, 10).btnItem}>
          <Text style={fontStyles(null, '400').rankText}>친구</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles(null, null, 10).btnItem}>
          <Text style={fontStyles(null, '400').rankText}>전체</Text>
        </TouchableOpacity>
      </View>

      <View style={styles(null, 10).btnContainer}>
        <TouchableOpacity
          style={styles('#bdd7ff').btnItem}
          onPress={() => setPeriodType('week')}>
          <Text style={fontStyles(null, '400').rankText}>주간</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles('#bdd7ff', null, 10).btnItem}
          onPress={() => setPeriodType('month')}>
          <Text style={fontStyles(null, '400').rankText}>월간</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles('#bdd7ff', null, 10).btnItem}
          onPress={() => setPeriodType('all')}>
          <Text style={fontStyles(null, '400').rankText}>누적</Text>
        </TouchableOpacity>
      </View>

      <PloggingTopRanking rankDataList={topRankData} />

      <PloggingBottomRanking rankDataList={bottomRankData} />
    </View>
  );
}

export default PloggingRankingScreen;
