<!--
 * @Author: HuRuiLin
 * @Date: 2023-04-05 19:24:23
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-11 12:35:42
-->
<template>
  <view class="container">
    <LeaveAduditList></LeaveAduditList>
  </view>
</template>

<script>
import LeaveAduditList from "./components/LeaveAuditList.vue";
export default {
  components: { LeaveAduditList },
  data() {
    return {};
  },
};
</script>

<style>
page {
  background-color: #ededed;
}
</style>

<style lang="scss" scoped></style>
