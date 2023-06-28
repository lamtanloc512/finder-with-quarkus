import { FC, Suspense, useContext, useState } from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import { Avatar, Breadcrumb, Button, Layout, Menu, Message, PageHeader, Radio } from '@arco-design/web-react';
import { IconHome, IconCalendar, IconCaretRight, IconCaretLeft, IconRobot, IconTag } from '@arco-design/web-react/icon';
import css from './AdminLayout.module.less';
import { routes } from '../routes';
import { useStore } from 'zustand';
import { useRouteStore } from '../repository/adminReporsitory';

const MenuItem = Menu.Item;
const SubMenu = Menu.SubMenu;
const Sider = Layout.Sider;
const Header = Layout.Header;
const Footer = Layout.Footer;
const Content = Layout.Content;

type Props = {
	children?: React.ReactNode | undefined;
};

// const siderRoutes = routes.filter(el => el.path?.startsWith("/admin"));

const AdminLayout: FC<Props> = ({ children }) => {
	const [collapsed, setCollapsed] = useState(false);
	const handleCollapsed = () => {
		setCollapsed(!collapsed);
	};
	const { adminRoutes } = useRouteStore();
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
						{adminRoutes?.map((route, _index) => {
							if (route.path == undefined) {
								return <></>;
							}
							return (
								<NavLink key={_index.toString()} to={route.path ?? route.path}>
									<MenuItem key={_index.toString()}>
										<IconHome />
										Dashboard
									</MenuItem>
								</NavLink>
							);
						})}
						{/* <NavLink to='/admin'>
							<MenuItem key='0.1'>
								<IconHome />
								Dashboard
							</MenuItem>
						</NavLink>
						<NavLink to='/admin/users'>
							<MenuItem key='1.1'>
								<IconTag />
								Users
							</MenuItem>
						</NavLink> */}
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
