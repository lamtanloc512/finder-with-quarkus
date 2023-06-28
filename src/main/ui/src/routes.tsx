import { MenuProps } from '@arco-design/web-react';
import '@arco-design/web-react/dist/css/arco.css';
import 'normalize.css';
import { IndexRouteObject, NonIndexRouteObject, Outlet } from 'react-router-dom';
import AdminApp from './AdminApp.tsx';
import App from './App.tsx';
import './index.css';
import DashboardView from './views/admin/dashboard/index.tsx';
import UsersView from './views/admin/users/index.tsx';
import LoginView from './views/login/index.tsx';
import { useRouteStore } from './repository/adminReporsitory.ts';

type Override<T, E> = Omit<T, keyof E> & E;

export type ViewMeta = Readonly<{ handle?: MenuProps }>;
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
		element: <App />,
		children: [
			{
				path: '/about',
				element: (
					<div>
						<h1>THIS IS ABOUT PAGE</h1>
						<Outlet />
					</div>
				),
				children: [
					{
						path: '/about/child',
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
		element: <AdminApp />,
		children: [
			{
				path: '/admin/',
				element: <DashboardView />,
			},
			{
				path: '/admin/users',
				element: <UsersView />,
			},
		],
	},
	{
		path: '/login',
		element: <LoginView />,
	},
];

