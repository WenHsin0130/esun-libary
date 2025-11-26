<script setup>
// PrimeVue DataTable
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import ColumnGroup from 'primevue/columngroup'   // optional
import Row from 'primevue/row'                   // optional

import Button from 'primevue/button'

import ConfirmDialog from 'primevue/confirmdialog' // 確認借閱畫面
import Toast from 'primevue/toast';
import { useConfirm } from "primevue/useconfirm"
import { useToast } from "primevue/usetoast"


import { ref } from 'vue'

const products = ref([
    { isbn: 'P001', name: 'Laptop', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P002', name: 'Laptop', author: 'Electronics', introduction: '台灣' },
    { isbn: 'P003', name: '123', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P001', name: 'Laptop', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P002', name: 'Laptop', author: 'Electronics', introduction: '台灣' },
    { isbn: 'P003', name: '123', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P001', name: 'Laptop', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P002', name: 'Laptop', author: 'Electronics', introduction: '台灣' },
    { isbn: 'P003', name: '123', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P001', name: 'Laptop', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P002', name: 'Laptop', author: 'Electronics', introduction: '台灣' },
    { isbn: 'P003', name: '123', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P001', name: 'Laptop', author: 'Electronics', introduction: '台灣最高的山' },
    { isbn: 'P002', name: 'Laptop', author: 'Electronics', introduction: '台灣' },
    { isbn: 'P003', name: '123', author: 'Electronics', introduction: '台灣最高的山' }
])


// 將借閱資訊 product 帶入表格 borrowItem 中
const borrowItem = (product) => {
    console.log('Borrow:', product)
    alert(`Borrow: ${product.name}`)
}

// 借閱按鈕連接的借閱資訊畫面
const confirm = useConfirm();
const toast = useToast();

const confirm1 = (isbn) => {
    // 從 products 資料中找到選擇的書籍的所有資訊 (使用 isbn 判斷) 
    const product = products.value.find(p => p.isbn === isbn)

    confirm.require({
        // 借閱視窗控制: 借閱或取消
        header: `借閱《${product.name}》`, // 書名
        bookname: product.name, // 書名
        author: product.author, // 作者
        introduction: product.introduction, // 內容簡介

        rejectProps: {
            label: '取消',
            severity: 'secondary',
            outlined: true
        },
        acceptProps: {
            label: '確認借閱'
        },
        accept: () => {
            toast.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });
        },
        reject: () => {
            toast.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected', life: 3000 });
        }
    })
}


</script>

<template>

    <div class="card">
        <!-- 借閱視窗與圖書資訊 -->
        <ConfirmDialog>
            <template #message="slotProps">
                <div class="flex flex-col items-center w-full gap-4 border-b border-surface-200 dark:border-surface-700" style="width: 30rem">
                    <p>書名：{{ slotProps.message.bookname }}</p>
                    <p>作者：{{ slotProps.message.author }}</p>
                    <p>介紹：{{ slotProps.message.introduction }}</p>
                </div>
            </template>
        </ConfirmDialog>
        <Toast position="top-center" />
        <!-- 所有圖書資訊表 -->
        <DataTable :value="products" stripedRows paginator :rows="10" :rowsPerPageOptions="[10, 20, 50]"
            tableStyle="min-width: 50rem">
            <Column field="isbn" header="ISBN (書號)"></Column>
            <Column field="name" header="書名"></Column>
            <Column field="author" header="作者"></Column>
            <Column field="introduction" header="內容簡介"></Column>
            <Column field="borrow" header="借閱">
                <template #body="slotProps">
                    <!-- 借閱按鈕 (#body 為自訂欄位必要 label) -->
                    <Button @click="confirm1(slotProps.data.isbn)" label="借閱" severity="success" variant="text" raised />
                </template>
            </Column>
        </DataTable>
    </div>

</template>