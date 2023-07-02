import { MenuProps } from '@arco-design/web-react';
import '@arco-design/web-react/dist/css/arco.css';
import { IconFile, IconSettings, IconUser } from '@arco-design/web-react/icon';
import 'normalize.css';
import { IndexRouteObject, NonIndexRouteObject, Outlet } from 'react-router-dom';
import AdminApp from './AdminApp.tsx';
import App from './App.tsx';
import './index.css';
import FinderView from './views/admin/finder/index.tsx';
import UsersView from './views/admin/users/index.tsx';
import LoginView from './views/login/index.tsx';

type Override<T, E> = Omit<T, keyof E> & E;

export type ViewMeta = Readonly<{
	name?: string;
	handle?: MenuProps;
	key: string;
	icon?: React.ReactNode;
	path: string;
	isRoot: boolean;
}>;
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
		isRoot: true,
		key: '0',
		element: <App />,
		children: [
			{
				path: '/about',
				key: '1',
				isRoot: false,
				element: (
					<div>
						<h1>THIS IS ABOUT PAGE</h1>
						<Outlet />
					</div>
				),
				children: [
					{
						path: '/about/child',
						isRoot: false,
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
		isRoot: true,
		key: '0',
		element: <AdminApp />,
		children: [
			{
				path: '/admin/workspace',
				name: 'Workspace',
				icon: <IconSettings />,
				key: '0_0',
				element: <UsersView />,
				isRoot: false,
				children: [
					{
						path: '/admin/workspace/user',
						name: 'User',
						icon: <IconUser />,
						key: '0_0_0',
						element: <UsersView />,
						isRoot: false,
					},
				],
			},
			{
				path: '/admin/finder',
				name: 'Finder',
				icon: <IconFile />,
				key: '0_1',
				element: <FinderView />,
				isRoot: false,
			},
		],
	},
	{
		path: '/login',
		key: '0',
		element: <LoginView />,
		isRoot: true,
	},
];
