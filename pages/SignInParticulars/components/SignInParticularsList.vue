<template>
  <view>
    <view class="container" @click="onclick()">
      <view class="box">
        <img src="../../../static/icon/icon-classList.png" alt="" />
        <view class="left-container">
          <p>lessonName</p>
          <p>考勤班级: className</p>
        </view>
      </view>
      <view class="right-container">
        <p class="statue">正在进行</p>
        <van-icon name="arrow" />
      </view>
    </view>
    <view class="container">
      <view class="box">
        <img src="../../../static/icon/icon-classList.png" alt="" />
        <view class="left-container">
          <p>lessonName</p>
          <p>考勤班级: className</p>
        </view>
      </view>
      <view class="right-container">
        <p class="statue">正在进行</p>
        <van-icon name="arrow" />
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      lessonName: "",
      className: "",
    };
  },
  methods: {
    onclick() {
      uni.navigateTo({
        url: "/pages/SignInParticulars/SignInHistory",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 5px 15px;
  .box {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  img {
    width: 30px;
    height: 30px;
  }
  .left-container {
    margin-left: 15px;
    p:nth-child(1) {
      font-size: 16px;
    }
    p:nth-child(2) {
      font-size: 12px;
      margin-top: 5px;
      color: $uni-text-color-placeholder;
    }
  }
  .right-container {
    display: flex;
    .statue {
      font-size: 14px;
      margin-right: 5px;
    }
  }
}
</style>
