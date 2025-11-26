<script setup>
import Toolbar from 'primevue/toolbar'
import Button from 'primevue/button'
import SplitButton from 'primevue/splitbutton'  // 下拉式按鈕
import InputText from 'primevue/inputtext'
import IconField from 'primevue/iconfield'
import InputIcon from 'primevue/inputicon'

import { ref } from 'vue';

const items = ref([
    {
        label: '查看個人資料',
        icon: 'pi pi-id-card'
    },
    {
        label: '借閱紀錄',
        icon: 'pi pi-bookmark'
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
                <SplitButton icon="pi pi-user" label="登入" :model="items"  severity="info"></SplitButton>
            </template>
        </Toolbar>
    </div>
</template>