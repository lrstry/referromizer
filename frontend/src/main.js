import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App.vue'
import VueClipboard from 'vue-clipboard2'
import VeeValidate from 'vee-validate'
import Icon from 'vue-awesome/components/Icon'
import Toasted from 'vue-toasted'
import VueSession from 'vue-session'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vue-awesome/icons'

Vue.use(BootstrapVue)
Vue.use(VueClipboard)
Vue.use(VeeValidate, {fieldsBagName: 'formFields'})
Vue.use(Toasted, { duration: "2500", position: "bottom-right" })
Vue.use(VueSession)

Vue.component('icon', Icon)

new Vue({ // eslint-disable-line no-new
  render: h => h(App)
}).$mount('#app')
