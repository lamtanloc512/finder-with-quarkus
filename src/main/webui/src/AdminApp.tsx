import AdminLayout from './layouts/AdminLayout.tsx';
import WorkspaceView from './views/admin/workspace/index.tsx';

function AdminApp() {
	return (
		<AdminLayout>
			<WorkspaceView />
		</AdminLayout>
	);
}

export default AdminApp;
