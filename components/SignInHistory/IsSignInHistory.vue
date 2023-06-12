<!--
 * @Author: HuRuiLin
 * @Date: 2023-02-24 17:16:34
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-04 20:19:20
-->
<template>
  <div class="isSignHistory-container">
    <p class="lesson-name">{{ lessonName }}</p>
    <div class="isSignHistory-right-container">
      <p class="signIn-status">{{ signInStatus }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "IsSignHistory",
  props: ["lessonName", "lessonTime", "signInStatus"],
  data() {
    return {
      property: "value",
    };
  },
};
</script>

<style lang="less" scoped>
.isSignHistory-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 48px;
  background-color: #f8f9fd;
  border: 1px solid #ebeef3;
  color: #8f8f8f;
  border-radius: 5px;
  margin-bottom: 20px;
  .lesson-name {
    margin-left: 10px;
  }
  .isSignHistory-right-container {
    display: flex;
    align-items: center;
    margin-right: 10px;
    .lesson-time {
      font-size: 13px;
      margin-right: 20px;
    }
  }
}
</style>
