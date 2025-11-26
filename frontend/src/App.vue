<script setup>
import HelloWorld from './components/HelloWorld.vue'

// PrimeVue DataTable
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import ColumnGroup from 'primevue/columngroup'   // optional
import Row from 'primevue/row'                   // optional

import { ref } from 'vue'

const products = ref([
  { code: 'P001', name: 'Laptop', category: 'Electronics', quantity: 10 },
  { code: 'P002', name: 'Mouse', category: 'Electronics', quantity: 25 },
  { code: 'P003', name: 'Keyboard', category: 'Electronics', quantity: 15 },
  { code: 'P004', name: 'Monitor', category: 'Electronics', quantity: 5 }
])

</script>

<template>
  <div class="card">
    <DataTable :value="products" stripedRows paginator :rows="5" :rowsPerPageOptions="[5, 10, 20, 50]" tableStyle="min-width: 50rem">
      <Column field="code" header="Code"></Column>
      <Column field="name" header="Name"></Column>
      <Column field="category" header="Category"></Column>
      <Column field="quantity" header="Quantity"></Column>
    </DataTable>
  </div>
  <div>
    <a href="https://vite.dev" target="_blank">
      <img src="/vite.svg" class="logo" alt="Vite logo" />
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
    </a>
  </div>
  <HelloWorld msg="Vite + Vue" />
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}

.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}

.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
