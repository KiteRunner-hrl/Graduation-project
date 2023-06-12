<!--
 * @Author: HuRuiLin
 * @Date: 2023-03-26 13:50:32
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-04-10 12:26:52
-->
<template>
  <view class="container">
    <view class="empty-container">
      <van-empty
        :description="emptyContent"
        image="/static/images/img-empty.png"
      >
        <van-button
          color="#0256ff"
          icon="plus"
          custom-style="width: 160px;  border-radius: 6px"
          @click="toCreateSignIn"
          >立即创建</van-button
        >
      </van-empty>
    </view>
  </view>
</template>

<script>
export default {
  props: ["emptyContent"],
  methods: {
    toCreateSignIn() {
      uni.navigateTo({
        url: "/pages/Index/createSignIn/createSignIn",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  .empty-container {
    text-align: center;
    p {
      font-size: 19px;
      font-weight: 700;
      color: $uni-text-color-empty;
    }
    /deep/ .van-empty__description {
      font-size: 19px;
      color: $uni-text-color-empty;
    }
  }
}
</style>
