import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import {Form, Field, CellGroup, Button, Cell} from 'vant';
import { Col, Row } from 'vant';
import { RadioGroup, Radio } from 'vant';
import { Tabbar, TabbarItem } from 'vant';

const app = createApp(App);
app.use(router)
    .use(Form)
    .use(Cell)
    .use(Field)
    .use(CellGroup)
    .use(Button)
    .use(RadioGroup)
    .use(Radio)
    .use(Col).use(Row).use(Tabbar).use(TabbarItem)
    .mount('#app')
