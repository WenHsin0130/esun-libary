<script setup>
import { Form } from '@primevue/forms';
import { reactive } from 'vue';
import { useToast } from 'primevue/usetoast';

import InputText from 'primevue/inputtext'
import Message from 'primevue/Message'
import Button from 'primevue/Button'

const toast = useToast();

// 設定帳號密碼初始值為空
const initialValues = reactive({
    username: '',
    userpassword: ''
});

const resolver = ({ values }) => {
    const errors = {};

    // 檢查帳號是否為空
    if (!values.username) {
        errors.username = [{ message: '請輸入使用者名稱' }];
    }

    // 檢查密碼是否為空
    if (!values.userpassword) {
        errors.userpassword = [{ message: '請輸入密碼' }];
    }

    return {
        values, // (Optional) Used to pass current form values to submit event.
        errors
    };
};

const onFormSubmit = ({ valid }) => {
    if (valid) {
        toast.add({
            severity: 'success',
            summary: 'Form is submitted.',
            life: 3000
        });
    }
};

</script>

<template>
    <h1>登入圖書館</h1>

    <div class="card flex justify-center">
        <Toast />

        <Form v-slot="$form" :initialValues :resolver @submit="onFormSubmit" class="flex flex-col gap-4 w-full sm:w-56">
            <div class="flex flex-col gap-1">
                <InputText name="username" type="text" placeholder="使用者 ID" fluid />
                <Message v-if="$form.username?.invalid" severity="error" size="small" variant="simple">{{
                    $form.username.error?.message }}</Message>
            </div>
            <div class="flex flex-col gap-1">
                <InputText name="userpassword" type="password" placeholder="密碼" fluid />
                <Message v-if="$form.userpassword?.invalid" severity="error" size="small" variant="simple">{{
                    $form.userpassword.error?.message }}</Message>
            </div>
            <Button type="submit" severity="secondary" label="Submit" />
        </Form>
    </div>
</template>