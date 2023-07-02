import { Outlet } from 'react-router-dom';

const WorkspaceView = () => {
	return (
		<div style={{ backgroundColor: 'white', padding: '8px', height: '100%' }}>
			<Outlet />
		</div>
	);
};

export default WorkspaceView;
