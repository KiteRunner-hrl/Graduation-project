<!--
 * @Author: HuRuiLin
 * @Date: 2023-02-23 18:04:21
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-09 16:42:27
-->
<!--
 * @ Author: HuRuiLin
 * @ Create Time: 2023 -02-23 18:04:21
 * @ Modified by: HuRuiLin
 * @ Modified time: 2023 -02-23 19:50:55
 -->
<template>
  <div class="waitSignHistory-container">
    <view class="left-container">
      <p class="lesson-name">{{ lessonName }}</p>
    </view>
    <div class="signHistory-right-container">
      <p class="signIn-status">{{ signInStatus }}</p>
      <van-icon name="arrow" color="#bfbfbf" />
    </div>
  </div>
</template>

<script>
export default {
  name: "waitSignHistory",
  props: ["lessonName", "signInStatus"],
  data() {
    return {
      property: "value",
      time: 30 * 60 * 1000,
    };
  },
};
</script>

<style lang="less" scoped>
.waitSignHistory-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 45px;
  background-color: #f5fafd;
  border: 1px solid #dee3f6;
  border-radius: 5px;
  margin-bottom: 21px;
  .left-container {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .lesson-name {
    margin-left: 10px;
  }
  .signHistory-right-container {
    display: flex;
    align-items: center;
    margin-right: 10px;
    .lesson-time {
      color: #0256ff;
      font-size: 13px;
      margin-right: 20px;
    }
    .signIn-status {
      color: #ff8c00;
      margin-right: 3px;
    }
  }
}
</style>
