import { HStack } from '@chakra-ui/react';
import { Link } from 'react-router';
import { ColorModeButton } from './ui/color-mode';

const Header = () => {
  return (
    <HStack
      m={2}
      py={2}
      px={4}
      justifyContent={'space-between'}
      bgColor={'bg.emphasized'}
      borderRadius={4}
      borderColor={'border.emphasized'}
      borderWidth={1}
    >
      <HStack gap={3}>
        <Link style={{ textDecoration: 'underline' }} to="/">
          Транспортные средства
        </Link>
        <Link style={{ textDecoration: 'underline' }} to="/coordinates">
          Координаты
        </Link>
        <Link style={{ textDecoration: 'underline' }} to="/specials">
          Специальные действия
        </Link>
      </HStack>
      <ColorModeButton />
    </HStack>
  );
};

export default Header;
