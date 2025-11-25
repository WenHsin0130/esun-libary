<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <form method="POST" action="/login">
      <input type="text" name="username" placeholder="Username" required />
      <input type="password" name="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>

export default {
  // 匯出組件，組件名稱: HelloWorld
  name: 'HelloWorld',
  props: {
    msg: String  // 名稱為 msg 的 prop，類型是 String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
