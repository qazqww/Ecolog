import React, { useEffect } from "react";

import styled from "styled-components";
import { Fade } from "react-awesome-reveal";
import AOS from 'aos';
import 'aos/dist/aos.css';

import logoImage from "../asset/image/earth_normal.gif";


const Container = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  margin-top: 60px;
`;

const LogoImage = styled.img`
  width: 640px;
  @media (max-width: 768px) {
    width: 100%;
    margin-bottom: 20px;
  }
`;

const PromotionEcolog = () => {
  useEffect(() => {
    AOS.init({
      duration : 1000
    });
  })

  return (
    <Container>
        <LogoImage src={logoImage} data-aos="fade-up">
        </LogoImage>
    </Container>
  )
};

export default PromotionEcolog;