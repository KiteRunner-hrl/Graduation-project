<!--
 * @Author: HuRuiLin
 * @Date: 2023-05-14 21:15:18
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-14 21:59:40
-->
<template>
  <view class="container">
    <!-- <view class="classMemberList-container" v-if="type == 'admin'">
      <van-tag color="#FF8C00" size="medium">管理员</van-tag>
      <view class="van-cell-text">{{ name }}</view>
    </view> -->
    <view class="classMemberList-container" style="margin-top: 5px;">
      <view class="left-container">
        <van-tag color="#0256ff" size="medium">学生</van-tag>
        <view class="van-cell-text">胡锐麟</view>
      </view>
      <view class="right-container" style="margin-right: 15px;">
        <van-button
          type="primary"
          size="small"
          color="#0256ff"
          custom-style="margin-right: 10px; border-radius: 5px"
          @click="openDialog"
          >修改</van-button
        >
        <van-button type="danger" size="small" custom-style="border-radius: 5px"
          >删除</van-button
        >
      </view>
    </view>
    <!-- <view class="classMemberList-container">
      <view class="left-container">
        <van-tag color="#70C86A" size="medium">学生</van-tag>
        <view class="van-cell-text">李红</view>
      </view>
      <view class="right-container" style="margin-right: 15px;">
        <van-button
          type="primary"
          size="small"
          color="#0256ff"
          custom-style="margin-right: 10px; border-radius: 5px"
          @click="openDialog"
          >修改</van-button
        >
        <van-button type="danger" size="small" custom-style="border-radius: 5px"
          >删除</van-button
        >
      </view>
    </view> -->
    <view class="classMemberList-container">
      <view class="left-container">
        <van-tag color="#70C86A" size="medium">学生</van-tag>
        <view class="van-cell-text">王五</view>
      </view>
      <view class="right-container" style="margin-right: 15px;">
        <van-button
          type="primary"
          size="small"
          color="#0256ff"
          custom-style="margin-right: 10px; border-radius: 5px"
          @click="openDialog"
          >修改</van-button
        >
        <van-button type="danger" size="small" custom-style="border-radius: 5px"
          >删除</van-button
        >
      </view>
    </view>
    <view class="classMemberList-container">
      <view class="left-container">
        <van-tag color="#70C86A" size="medium">学生</van-tag>
        <view class="van-cell-text">赵六</view>
      </view>
      <view class="right-container" style="margin-right: 15px;">
        <van-button
          type="primary"
          size="small"
          color="#0256ff"
          custom-style="margin-right: 10px; border-radius: 5px"
          @click="openDialog"
          >修改</van-button
        >
        <van-button type="danger" size="small" custom-style="border-radius: 5px"
          >删除</van-button
        >
      </view>
    </view>

    <van-dialog
      use-slot
      title="选择用户权限"
      :show="show"
      @close="close"
      @getuserinfo="getUserInfo"
      :showConfirmButton="false"
      :close-on-click-overlay="true"
    >
      <view
        class="input-container"
        style="
          display: flex;
          align-items: center;
          justify-content: space-between;
          margin: 20px 30px;
        "
      >
        <van-button
          block
          icon="contact"
          color="#0256ff"
          custom-style="border-radius: 5px; margin-bottom: 5px"
          >教师</van-button
        >
        <van-button
          block
          icon="contact"
          color="#FF8C00"
          custom-style="border-radius: 5px; margin-bottom: 5px"
          >管理员</van-button
        >
      </view>
    </van-dialog>
  </view>
</template>

<script>
export default {
  props: ["type", "name"],
  data() {
    return {
      show: false,
    };
  },
  methods: {
    openDialog() {
      this.show = true;
    },
    close() {
      this.show = false;
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  .classMemberList-container {
    background-color: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 0;
    margin-bottom: 5px;
    van-tag,
    .van-cell-text {
      margin-left: 15px;
    }
    .left-container,
    .right-container {
      display: flex;
      align-items: center;
    }
  }
}
</style>
