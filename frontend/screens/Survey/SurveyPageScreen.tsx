import React from 'react';
import {Text, View, TouchableHighlight, StyleSheet} from 'react-native';
import {Button} from 'react-native-paper';

function SurveyPageScreen({navigation}: any) {
  const [step, setStep] = React.useState(0);
  const [point, setPoint] = React.useState([]);
  let result = 0;
  const survey = [
    {q: '1. 화분은 일반 쓰레기이다.', a1: '네', a2: '아니오'},
    {
      q: '2. 고추장은 음식물 쓰레기이다.',
      a1: '네',
      a2: '아니오',
    },
    {q: '3. 칫솔은 플라스틱 재활용 쓰레기다.', a1: '네', a2: '아니오'},
    {q: '4. 빨대는 일반 쓰레기이다.', a1: '네', a2: '아니오'},
    {q: '5. 당신은 바보입니까?', a1: '네', a2: '예'},
  ];
  const routeNext = (p: number) => {
    if (step === 4) {
      setPoint(point.slice(0, point.length - 1));
      setPoint([...point, p]);
      result = 0;
      for (let i = 0; i < point.length; i++) {
        result += point[i];
      }
      navigation.navigate('SurveyResult', {result: result});
      setPoint([]);
      setStep(0);
    } else {
      setStep(step + 1);
      setPoint([...point, p]);
    }
  };
  const routePrev = () => {
    if (step !== 0) {
      setStep(step - 1);
      setPoint(point.slice(0, point.length - 1));
    }
  };

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignItems: 'center',
    },
    question: {
      fontSize: 25,
      fontWeight: '700',
      marginBottom: '60%',
      marginTop: '30%',
    },
    button: {
      backgroundColor: 'rgb(0, 242, 96)',
      width: '80%',
      height: '8%',
      marginTop: '10%',
      justifyContent: 'center',
    },
    buttonText: {
      color: '#000',
      fontSize: 20,
      fontWeight: '600',
    },
    back: {
      marginRight: 'auto',
    },
  });
  return (
    <View style={styles.container}>
      <TouchableHighlight
        style={styles.back}
        onPress={() => {
          routePrev();
        }}>
        <Text>이전</Text>
      </TouchableHighlight>
      <Text style={styles.question}>{survey[step].q}</Text>
      <Button
        mode="contained"
        style={styles.button}
        onPress={() => {
          routeNext(10);
        }}>
        <Text style={styles.buttonText}>{survey[step].a1}</Text>
      </Button>
      <Button
        mode="contained"
        style={styles.button}
        onPress={() => {
          routeNext(0);
        }}>
        <Text style={styles.buttonText}>{survey[step].a2}</Text>
      </Button>
    </View>
  );
}

export default SurveyPageScreen;
