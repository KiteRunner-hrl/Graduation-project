<template>
  <view class="container">
    <van-icon name="checked" size="70px" color="#65B85D" />
    <p>注册成功</p>
    <van-button @click="onSubmit()">返回登录</van-button>
  </view>
</template>

<script>
export default {
  methods: {
    onSubmit() {
      uni.navigateTo({
        url: "/pages/Login/Login",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  padding: 35px 0;
  text-align: center;
  p {
    margin-top: 15px;
  }
}
/deep/ .van-button {
  width: 160px;
  background-color: $uni-color-primary;
  color: $uni-text-color-inverse;
  border-radius: $uni-border-radius-base;
  margin: 40px 0;
}
</style>
