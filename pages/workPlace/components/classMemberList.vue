<!--
 * @Author: HuRuiLin
 * @Date: 2023-04-02 12:07:39
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 20:51:34
-->
<template>
  <view>
    <view class="classMemberList-container" v-if="type == 'admin'">
      <van-tag color="#FF8C00" size="medium">管理员</van-tag>
      <view class="van-cell-text">{{ name }}</view>
    </view>
    <view class="classMemberList-container" v-if="type == 'teacher'">
      <van-tag color="#70C86A" size="medium">学生</van-tag>
      <view class="van-cell-text">{{ name }}</view>
    </view>
    <view class="classMemberList-container" v-if="type == 'user'">
      <van-tag color="#70C86A" size="medium">学生</van-tag>
      <view class="van-cell-text">{{ name }}</view>
    </view>
  </view>
</template>

<script>
export default {
  props: ["type", "name"],
  data() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
.classMemberList-container {
  display: flex;
  align-items: center;
  padding: 5px 0;
  margin-bottom: 5px;
  van-tag,
  .van-cell-text {
    margin-left: 15px;
  }
}
</style>
