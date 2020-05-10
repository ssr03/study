<template>
  <div>
    <h1 @click="handler(arg, event)">제목</h1>
    <div>
      <ul>
        <li>list</li>
      </ul>
    </div>
  </div>
</template>

<script>
import { libName } from 'libName'
export default {
  data() {
    return {
      quote: 'I want to be single'
    }
  }
}
</script>

<style></style>
