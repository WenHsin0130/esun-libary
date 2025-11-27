<script setup>
import Toolbar from 'primevue/toolbar'
import Button from 'primevue/button'
import SplitButton from 'primevue/splitbutton'  // 下拉式按鈕
import InputText from 'primevue/inputtext'
import IconField from 'primevue/iconfield'
import InputIcon from 'primevue/inputicon'

import { ref } from 'vue';
import { useRouter } from 'vue-router'

// 頁面跳轉功能
const router = useRouter()

// 點擊登入跳轉登入頁面
const goLogin = () => {
  router.push('/login')
}

const items = ref([
    {
        label: '查看個人資料',
        icon: 'pi pi-id-card',
        command: () => router.push('/personal-data')
    },
    {
        label: '借閱紀錄',
        icon: 'pi pi-bookmark',
        command: () => router.push('/borrow-record')
    }
])


</script>

<template>
    <div class="card">
        <Toolbar style="border-radius: 3rem; padding: 1rem 1rem 1rem 1.5rem">
            <template #center>
                <IconField>
                    <InputIcon>
                        <i class="iconfont pi pi-search" />
                    </InputIcon>
                    <InputText placeholder="Search" />
                </IconField>
            </template>

            <template #end>
                <SplitButton @click="goLogin" icon="pi pi-user" label="登入" :model="items" severity="info"></SplitButton>
            </template>
        </Toolbar>
    </div>
</template>