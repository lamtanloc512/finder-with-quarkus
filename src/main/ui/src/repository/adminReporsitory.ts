import { create } from 'zustand';
import { ViewRouteObject } from '../routes';

type AdminStore = {
	adminRoutes: ViewRouteObject[];
	init: (adminRoutes: ViewRouteObject[]) => void;
};

export const useRouteStore = create<AdminStore>()((set) => ({
	adminRoutes: [],
	init: (routes) =>
		set((state) => ({
			...state,
			adminRoutes: [...routes.filter((el) => el.path?.startsWith('/admin'))],
		})),
}));
