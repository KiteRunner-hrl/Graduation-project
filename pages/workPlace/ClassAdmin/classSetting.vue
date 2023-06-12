<!--
 * @Author: HuRuiLin
 * @Date: 2023-04-02 22:28:18
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 22:07:31
-->
<template>
  <view class="classSetting-container">
    <view class="classMessage">
      <p class="title">大学英语III</p>
      <p class="classPassword">班级口令：FGSEDHJFGSDJHG</p>
      <p class="tipText">输入口令加入班级</p>
    </view>
    <van-button block type="danger">解散班级</van-button>
  </view>
</template>

<script>
export default {
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

<style lang="scss" scoped>
.classSetting-container {
  padding: 0 15px;
  margin-top: 15px;
  .classMessage {
    border-radius: 6px;
    background-color: $uni-bg-color;
    text-align: center;
    padding: 20px 0;
    .title {
      font-size: 16px;
    }
    .classPassword {
      color: $uni-border-plain;
      font-size: 18px;
      font-weight: bold;
      margin-top: 25px;
    }
    .tipText {
      color: $uni-text-color-grey;
      margin-top: 25px;
    }
  }
}
/deep/ .van-button {
  border-radius: $uni-border-radius-base;
  margin: 30px 0;
}
</style>
