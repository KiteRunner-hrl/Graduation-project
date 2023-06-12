<template>
  <view class="container">
    <view class="empty-container">
      <van-empty
        :description="emptyContent"
        image="/static/images/img-empty.png"
      >
      </van-empty>
    </view>
  </view>
</template>

<script>
export default {
  props: ["emptyContent"],
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
