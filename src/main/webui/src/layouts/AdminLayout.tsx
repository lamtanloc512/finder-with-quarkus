/* eslint-disable no-mixed-spaces-and-tabs */
import { Avatar, Layout, Menu, PageHeader, Skeleton } from '@arco-design/web-react';
import { IconCaretLeft, IconCaretRight } from '@arco-design/web-react/icon';
import { isNull } from 'lodash';
import { FC, Fragment, Suspense, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { useRouteStore } from '../repository/AdminReporsitory';
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

	const renderJsxRoute = (paramRoutes: ViewRouteObject[] | undefined): React.ReactNode => {
		return paramRoutes?.map((route) => {
			if (isNull(route)) {
				return;
			}
			if (route.isRoot) {
				return renderJsxRoute(route.children);
			}
			if (route.children?.length) {
				return (
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
				);
			}
			return (
				<NavLink key={route.key} to={route.path}>
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
						onClickMenuItem={(key) => null}
						onClickSubMenu={(key, keyPath) => null}
						mode='vertical'
						style={{ width: '100%' }}>
						{renderJsxRoute(adminRoutes)}
					</Menu>
				</Sider>
				<Layout style={{ backgroundColor: 'var(--color-fill-2)' }}>
					<Layout style={{ padding: '8px' }}>
						<Suspense fallback={<Skeleton />}>{children}</Suspense>
					</Layout>
				</Layout>
			</Layout>
		</div>
	);
};

export default AdminLayout;
