<!--
 * @Author: HuRuiLin
 * @Date: 2023-02-21 21:45:59
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-03-14 12:19:51
-->
<template>
  <div class="lesson-container">
    <p class="lesson-name">{{ lessonName }}</p>
    <p class="lesson-time">{{ lessonTime }}</p>
  </div>
</template>

<script>
export default {
  name: "Lesson",
  props: ["lessonName", "lessonTime"],
  data() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
.lesson-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 45px;
  background-color: #f5fafd;
  border: 1px solid #dee3f6;
  border-radius: 5px;
  margin-bottom: 20px;
  .lesson-name {
    margin-left: 10px;
  }
  .lesson-time {
    margin-right: 10px;
    color: $uni-color-primary;
    font-size: 13px;
  }
}
</style>
