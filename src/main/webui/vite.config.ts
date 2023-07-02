import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react-swc';
import { resolve } from 'path';
import express from 'express';

const app = express();
app.get('/', (req, res) => {
	res.send('Allow detection by Quinoa').end();
});

function expressPlugin() {
	return {
		name: 'express-plugin',
		configureServer(server) {
			server.middlewares.use(app);
		},
	};
}

export default defineConfig({
	plugins: [expressPlugin(), react()],
	build: {
		rollupOptions: {
			input: {
				quinoa: resolve(__dirname, 'index.html'),
			},
		},
	},
});
