import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import {Form, Field, CellGroup, Button, Cell} from 'vant';
import { Col, Row } from 'vant';
import { RadioGroup, Radio } from 'vant';
import { Tabbar, TabbarItem } from 'vant';
import store from './store';
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'

const app = createApp(App);
app.use(router)
    .use(store)
    .use(Form)
    .use(Cell)
    .use(Field)
    .use(CellGroup)
    .use(Button)
    .use(RadioGroup)
    .use(Radio)
    .use(Col).use(Row).use(Tabbar).use(TabbarItem)
    .component("font-awesome-icon", FontAwesomeIcon)
    .mount('#app')
