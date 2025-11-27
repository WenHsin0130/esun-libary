<script setup>
// PrimeVue DataTable
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'

import Button from 'primevue/button'

import ConfirmDialog from 'primevue/confirmdialog' // 確認借閱畫面
import Toast from 'primevue/toast';
import { useConfirm } from "primevue/useconfirm"
import { useToast } from "primevue/usetoast"

import { ref, onMounted } from 'vue'


// ref([]) 表示 books 的初始值是一個空陣列
const books = ref([])

onMounted(async () => {
    // onMounted 是 Vue 的生命週期函式，當組件掛載到畫面上後會自動執行裡面的程式碼。
    // 呼叫後端 Controller，將取得的值代入 books
    const res = await fetch('http://localhost:8080/api/books')
    books.value = await res.json()
})
console.log(books)

// 將借閱資訊 book 帶入表格 borrowItem 中
const borrowItem = (book) => {
    console.log('Borrow:', book)
    alert(`Borrow: ${book.name}`)
}

/** 
 * 借閱按鈕連接的借閱資訊畫面 
 * confirm : 借閱視窗
 * toast : 借閱提示/取消提示
*/
const confirm = useConfirm();
const toast = useToast();

const confirm1 = (isbn) => {
    // 從 books 資料中找到選擇的書籍的所有資訊 (使用 isbn 判斷) 
    const book = books.value.find(p => p.isbn === isbn)

    confirm.require({
        // 借閱視窗控制: 借閱或取消
        header: `借閱《${book.name}》`, // 書名
        bookname: book.name, // 書名
        author: book.author, // 作者
        introduction: book.introduction, // 內容簡介

        // 借閱視窗設計，包含文字、色彩等
        rejectProps: {
            label: '取消',
            severity: 'secondary',
            outlined: true
        },
        acceptProps: {
            label: '確認借閱'
        },

        // 借閱訊息: 接受出現借閱成功，反之則出現取消
        accept: () => {
            toast.add({ severity: 'info', summary: '借閱成功', detail: `已完成《${book.name}》借閱`, life: 3000 });
        },
        reject: () => {
            toast.add({ severity: 'error', summary: '取消', detail: '取消借閱', life: 3000 });
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
        <DataTable :value="books" stripedRows paginator :rows="10" :rowsPerPageOptions="[10, 20, 50]"
            tableStyle="margin: 0 auto;">
            <Column field="ISBN" header="ISBN (書號)"></Column>
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