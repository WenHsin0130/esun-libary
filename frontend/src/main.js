import { createApp } from 'vue'
import './style.css'
import PrimeVue from 'primevue/config'
import App from './App.vue'
import Aura from '@primeuix/themes/aura'
import './main.scss'
import 'primeicons/primeicons.css'

// Primevue 確認視窗與提示通知
import ConfirmationService from 'primevue/confirmationservice'
import ToastService from 'primevue/toastservice'

// 頁面跳轉(router)
import { createRouter, createWebHistory } from "vue-router"

// router 頁面引入
import PersonalData from "./pages/PersonalData.vue"
import BookData from './pages/BookData.vue'
import Login from "./pages/Login.vue"
import BorrowRecord from './pages/BorrowRecord.vue'


const app = createApp(App)
app.use(PrimeVue, {
    theme: {
        preset: Aura
    }
})

// 用createRouter方法定義出 Router 實體
const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: BookData },
    { path: '/personal-data', component: PersonalData },
    { path: '/login', component: Login },
    { path: '/borrow-record', component: BorrowRecord }
  ],
});

app.use(router)
app.use(ConfirmationService)
app.use(ToastService)

app.mount('#app')
