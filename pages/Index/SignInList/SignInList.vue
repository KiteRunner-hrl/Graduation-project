<!--
 * @Author: HuRuiLin
 * @Date: 2023-05-06 21:57:05
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 16:06:26
-->
<template>
  <view class="container">
    <view class="list">
      <Empty
        :emptyContent="emptyContent"
        v-if="signInLessonData.length == 0 && role == 'teacher'"
      ></Empty>
      <Empty_user
        :emptyContent="emptyContent"
        v-if="signInLessonData.length == 0 && role == 'user'"
      ></Empty_user>
      <SignInStatus
        v-if="signInLessonData.length != 0"
        :lessonName="lessonName"
        :signInNumber="signInNumber"
      ></SignInStatus>
    </view>
  </view>
</template>

<script>
import WaitSignHistory from "../../../components/SignInHistory/WaitSignHistory.vue";
import SignInStatus from "../../../components/SignInStatus/SignInStatus.vue";
export default {
  components: { WaitSignHistory, SignInStatus },
  data() {
    return {
      role: "",
      classId: 0,
      lessonName: "",
      signInNumber: 0,
      signInLessonData: [],
      signInStart: false,
      emptyContent: "暂无考勤活动",
    };
  },
  created() {
    uni.getStorage({
      key: "classId",
      success: (res) => {
        this.classId = res.data;
      },
    });
    uni.getStorage({
      key: "role",
      success: (res) => {
        this.role = res.data;
      },
    });
  },
  mounted() {
    this.getSignInList();
  },
  methods: {
    //根据班级ID查看已创建的签到
    getSignInList() {
      uni.request({
        url: "https://www.huruilinweb.top/KbdBackEnd/api/student/searchSignIn",
        methods: "GET",
        data: {
          classId: this.classId,
        },
        success: (res) => {
          console.log(res.data);
          uni.setStorage({ key: "signInList", data: res.data })
          this.signInLessonData = res.data;
          for (let index = 0; index < res.data.length; index++) {
            this.lessonName = res.data[index].lessonName;
            this.signInNumber = res.data[index].signInCount;
            uni.setStorage({ key: "signInID", data: res.data[index].id });
          }
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  padding: 10px 15px;
}
</style>
