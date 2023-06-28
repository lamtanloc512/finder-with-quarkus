import { FC } from 'react';
import { Outlet } from 'react-router-dom';

type Props = {
	children?: React.ReactNode | undefined;
};

const MainLayout: FC<Props> = ({ children }) => {
	return (
		<div>
			<h1>MainLayout</h1>
			{children}
		</div>
	);
};

export default MainLayout;
