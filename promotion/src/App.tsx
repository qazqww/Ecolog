import React from 'react';
import logo from './logo.svg';
import './App.css';

import styled from "styled-components";
import color from "./common/thema";

import PromotionTop from './components/PromotionTop';
import PromotionContent from './components/PromotionContent';

const AppDiv = styled.div`
  backgroud-color: ${color.white.default};
`;


function App() {
  return (
    <AppDiv>
      <PromotionTop />
      <PromotionContent />
    </AppDiv>
  );
}

export default App;
