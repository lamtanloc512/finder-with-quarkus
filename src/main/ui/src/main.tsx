import ReactDOM from 'react-dom/client';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { routes } from './routes';
import { Fragment, useMemo } from 'react';
import { useRouteStore } from './repository/adminReporsitory';

const router = createBrowserRouter([...routes]);

const Main = () => {
	const { init } = useRouteStore();
	useMemo(() => {
		init([...routes]);
	}, []);

	return (
		<Fragment>
			<RouterProvider router={router} />
		</Fragment>
	);
};

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(<Main />);
