import dayjs from 'dayjs';

const Date = ({ children }: { children: string }) => {
  const date = dayjs(children.replace('[UTC]', ''))
    .locale('ru')
    .format('DD.MM.YYYY / HH:mm');
  return <span>{date}</span>;
};

export default Date;
