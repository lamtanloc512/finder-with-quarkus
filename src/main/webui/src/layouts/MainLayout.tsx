import { FC } from 'react';

type Props = {
	children?: React.ReactNode;
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
