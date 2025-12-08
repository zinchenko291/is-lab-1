import { Container } from '@chakra-ui/react';
import { Outlet } from 'react-router';
import Header from './Header';

const Layout = () => {
  return (
    <Container>
      <Header />
      <Outlet />
    </Container>
  );
};

export default Layout;
