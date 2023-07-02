import { Grid, Image, Input, Layout, Menu } from '@arco-design/web-react';
import { IconApps, IconHome } from '@arco-design/web-react/icon';
import css from './index.module.less';

const SubMenu = Menu.SubMenu;
const MenuItem = Menu.Item;
const InputSearch = Input.Search;
const Row = Grid.Row;
const Col = Grid.Col;
const Content = Layout.Content;

const FinderView = () => (
	<Layout className={css['layout-wrapper']}>
		<Row className={css['layout-wrapper-search-wrapper']}>
			<Col span={1} style={{ display: 'flex', justifyContent: 'center' }}>
				<IconHome />
			</Col>
			<Col span={15}>
				<InputSearch allowClear placeholder='Enter keyword to search' />
			</Col>
		</Row>
		<Layout style={{ height: '100%', display: 'grid', gridTemplateColumns: 'auto 1fr' }}>
			<Menu
				style={{ width: 200, height: '100%', borderRight: '1px solid var(--color-fill-3)' }}
				defaultOpenKeys={['0']}
				defaultSelectedKeys={['0_1']}>
				<SubMenu
					key='0'
					title={
						<>
							<IconApps /> My Files
						</>
					}>
					<MenuItem key='0_0'>Menu 1</MenuItem>
					<MenuItem key='0_1'>Menu 2</MenuItem>
				</SubMenu>
			</Menu>
			<Content
				style={{
					paddingLeft: '8px',
					display: 'grid',
					gap: '8px',
					gridTemplateColumns: 'repeat(auto-fill, minmax(100px,1fr))',
					gridAutoRows: 'minmax(100, 1fr)',
				}}>
				<Image
					width={100}
					src='//p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a8c8cdb109cb051163646151a4a5083b.png~tplv-uwbnlip3yd-webp.webp'
					alt='lamp'
				/>
				<Image
					width={100}
					src='//p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a8c8cdb109cb051163646151a4a5083b.png~tplv-uwbnlip3yd-webp.webp'
					alt='lamp'
				/>
			</Content>
		</Layout>
	</Layout>
);

export default FinderView;
