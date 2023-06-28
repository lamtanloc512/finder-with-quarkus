import { MenuProps } from '@arco-design/web-react';
import '@arco-design/web-react/dist/css/arco.css';
import 'normalize.css';
import { IndexRouteObject, NonIndexRouteObject, Outlet } from 'react-router-dom';
import AdminApp from './AdminApp.tsx';
import App from './App.tsx';
import './index.css';
import UsersView from './views/admin/users/index.tsx';
import LoginView from './views/login/index.tsx';
import WorkspaceView from './views/admin/workspace/index.tsx';
import { IconHome } from '@arco-design/web-react/icon';

type Override<T, E> = Omit<T, keyof E> & E;

export type ViewMeta = Readonly<{ name?: string; handle?: MenuProps; key: string; icon?: React.ReactNode, path: string }>;
export type IndexViewRouteObject = Override<IndexRouteObject, ViewMeta>;
export type NonIndexViewRouteObject = Override<
	Override<NonIndexRouteObject, ViewMeta>,
	{
		children?: ViewRouteObject[];
	}
>;
export type ViewRouteObject = IndexViewRouteObject | NonIndexViewRouteObject;

export const routes: readonly ViewRouteObject[] = [
	{
		path: '/',
		key: '0',
		element: <App />,
		children: [
			{
				path: '/about',
				key: '1',
				element: (
					<div>
						<h1>THIS IS ABOUT PAGE</h1>
						<Outlet />
					</div>
				),
				children: [
					{
						path: '/about/child',
						key: '1_1',
						element: (
							<div>
								<h1>THIS IS ABOUT CHILD PAGE</h1>
							</div>
						),
					},
				],
			},
		],
	},
	{
		path: '/admin',
		name: 'Dashboard',
		icon: <IconHome />,
		key: '0',
		element: <AdminApp />,
		children: [
			{
				path: '/admin/',
				name: 'Workspace',
				key: '0_0',
				icon: <IconHome />,
				element: <WorkspaceView />,
				children: []
			},
			{
				path: '/admin/users',
				key: '0_1',
				name: 'User',
				icon: <IconHome />,
				element: <UsersView />,
				children: []
			},
		],
	},
	{
		path: '/login',
		key: '0',
		element: <LoginView />,
	},
];
