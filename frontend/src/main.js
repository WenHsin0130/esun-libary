import { createApp } from 'vue'
import './style.css'
import PrimeVue from 'primevue/config'
import App from './App.vue'
import Aura from '@primeuix/themes/aura'
import './main.scss'
import 'primeicons/primeicons.css'

import ConfirmationService from 'primevue/confirmationservice'
import ToastService from 'primevue/toastservice'



const app = createApp(App)
app.use(PrimeVue, {
    theme: {
        preset: Aura
    }
})

app.use(ConfirmationService)
app.use(ToastService)

app.mount('#app')
