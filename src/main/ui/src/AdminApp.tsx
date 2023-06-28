import { Outlet } from 'react-router-dom';
import AdminLayout from './layouts/AdminLayout.tsx';

function AdminApp() {
	return (
		<AdminLayout>
			<Outlet />
		</AdminLayout>
	);
}

export default AdminApp;
