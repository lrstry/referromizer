import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App.vue'
import VueClipboard from 'vue-clipboard2'
import VeeValidate from 'vee-validate'
import Icon from 'vue-awesome/components/Icon'
import Toasted from 'vue-toasted'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vue-awesome/icons'

Vue.use(BootstrapVue)
Vue.use(VueClipboard)
Vue.use(VeeValidate)
Vue.use(Toasted, { duration: "3500", position: "bottom-right" })

Vue.component('icon', Icon)

new Vue({ // eslint-disable-line no-new
  el: '#app',
  render: h => h(App)
})
