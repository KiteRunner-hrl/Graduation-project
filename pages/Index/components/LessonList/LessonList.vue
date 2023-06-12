<!--
 * @Author: HuRuiLin
 * @Date: 2023-05-06 21:37:44
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 16:11:51
-->
<template>
  <view class="container">
    <view class="title">
      <p style="font-weight: 700; font-size: 19px">{{ className }}</p>
      <van-tag type="warning" size="medium" v-if="signInList.length != 0"
        >新增签到</van-tag
      >
      <van-tag
        color="#ffffff"
        size="medium"
        text-color="#58727e"
        v-if="signInList.length == 0"
        >暂无考勤</van-tag
      >
    </view>
    <view class="bottom">
      <view class="bottom_left">
        <van-icon name="friends-o" size="20px" />
        <p style="margin-left: 3px">{{ classNum }}人</p>
      </view>
      <p>教师：{{ teacher }}</p>
    </view>
  </view>
</template>

<script>
export default {
  props: ["className", "classNum", "teacher"],
  data() {
    return {
      signInList: [],
    };
  },
  created() {
    uni.getStorage({
      key: "signInList",
      success: (res) => {
        this.signInList = res.data;
      },
    });
  },
};
</script>

<style lang="scss" scoped>
.container {
  height: 100px;
  padding: 10px;
  margin-bottom: 10px;
  background: linear-gradient(to right, #40a6f1, #1c97f8);
  border-radius: 5px;
  color: aliceblue;
  .title {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .bottom {
    bottom: 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
    line-height: 125px;
    .bottom_left {
      display: flex;
      align-items: center;
      justify-content: space-between;
    }
  }
}
</style>
