import { Button, Space } from '@arco-design/web-react';
import { useState } from 'react';

const UsersView = () => {
	const [text, setText] = useState<string>('');

	function callApi(): void {
		fetch('/api/hello')
			.then((val) => val.text())
			.then((val) => setText(val))
			.catch((err) => {
				console.log(err);
			});
	}

	return (
		<div>
			<h1>{text}</h1>
			<Space direction='horizontal'>
				<Button type='primary' onClick={() => callApi()}>
					Call Api
				</Button>
				<Button type='outline' onClick={() => setText('')}>
					Reset
				</Button>
			</Space>
		</div>
	);
};

export default UsersView;
