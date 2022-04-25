/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  View,
} from 'react-native';

function App() {
  return (
    <SafeAreaView>
      <StatusBar />
      <ScrollView>
        <View style={styles.container}>
          <Text style={styles.ecolog}>Ecolog</Text>
        </View>
        <View style={styles.container}>
          <Text style={styles.ecolog}>build test</Text>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    height: 150,
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  ecolog: {
    color: 'black',
    fontSize: 30,
  },
});

export default App;
