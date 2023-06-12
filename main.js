/*
 * @Author: HuRuiLin
 * @Date: 2023-02-21 18:23:22
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-06 16:30:15
 */
import Vue from "vue";
import App from "./App";
import axios from "axios";
import store from "./store";

Vue.config.productionTip = false;
Vue.use(axios);
App.mpType = "app";
const app = new Vue({
  ...App,
  store,
});
app.$mount();
