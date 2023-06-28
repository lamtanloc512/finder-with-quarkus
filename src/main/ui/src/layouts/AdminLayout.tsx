/* eslint-disable no-mixed-spaces-and-tabs */
import { Avatar, Layout, Menu, Message, PageHeader } from '@arco-design/web-react';
import { IconCaretLeft, IconCaretRight } from '@arco-design/web-react/icon';
import { FC, Fragment, Suspense, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { useRouteStore } from '../repository/adminReporsitory';
import { ViewRouteObject } from '../routes';
import css from './AdminLayout.module.less';

const MenuItem = Menu.Item;
const SubMenu = Menu.SubMenu;
const Sider = Layout.Sider;

type Props = {
	children?: React.ReactNode;
};

const AdminLayout: FC<Props> = ({ children }) => {
	const [collapsed, setCollapsed] = useState(false);
	const handleCollapsed = () => {
		setCollapsed(!collapsed);
	};
	const { adminRoutes } = useRouteStore();

	const renderJsxRoute = (paramRoutes: ViewRouteObject[]): React.ReactNode => {
		return paramRoutes.map((route) => {
			if (route.children?.length) {
				return (
					<NavLink to={route.path}>
						<SubMenu
							key={route.key}
							title={
								<Fragment>
									{route.icon}
									{route.name}
								</Fragment>
							}>
							{renderJsxRoute(route.children)}
						</SubMenu>
					</NavLink>
				);
			}
			return (
				<NavLink to={route.path}>
					<MenuItem key={route.key}>
						{route.icon} {route.name}
					</MenuItem>
				</NavLink>
			);
		});
	};

	return (
		<div className={css['main-layout']}>
			<PageHeader className={css['main-layout-header']} title='ADMIN' subTitle='WORKSPACE' extra={<Avatar>A</Avatar>} />
			<Layout className={css['main-layout-wrapper']}>
				<Sider
					className={css['main-layout-wrapper-sider']}
					collapsed={collapsed}
					onCollapse={handleCollapsed}
					collapsible
					trigger={collapsed ? <IconCaretRight /> : <IconCaretLeft />}
					breakpoint='xl'>
					<div className='logo' />
					<Menu
						defaultOpenKeys={['1']}
						defaultSelectedKeys={['0_3']}
						onClickMenuItem={(key) =>
							Message.info({
								content: `You select ${key}`,
								showIcon: true,
							})
						}
						mode='pop'
						style={{ width: '100%' }}>
						{renderJsxRoute(adminRoutes)}
					</Menu>
				</Sider>
				<Layout>
					<Layout style={{ padding: '0 24px' }}>
						<Suspense fallback={<h2>Loading ...</h2>}>{children}</Suspense>
					</Layout>
				</Layout>
			</Layout>
		</div>
	);
};

export default AdminLayout;
