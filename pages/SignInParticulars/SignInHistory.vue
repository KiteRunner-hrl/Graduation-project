<template>
  <view class="container">
    <SignInHistoryList></SignInHistoryList>
  </view>
</template>

<script>
import SignInHistoryList from "./components/SignInHistoryList.vue";
export default {
  components: { SignInHistoryList },
  data() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
.container {
  padding: 10px 0;
}
</style>
