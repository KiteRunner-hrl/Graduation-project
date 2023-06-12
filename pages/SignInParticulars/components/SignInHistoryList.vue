<template>
  <view class="container">
    <view class="isLeave" v-for="item in signInList" :key="item.index">
      <p>{{ item.username }}</p>
      <p>已签到</p>
    </view>
    <view class="isSignIn" v-for="item in leaveList" :key="item.index">
      <p>{{ item.userName }}</p>
      <p>已请假</p>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      leaveList: [],
      signInList: [],
      classId: 0,
			signInID: 0,
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
      key: "fullName",
      success: (res) => {
        this.username = res.data;
      },
    });
		uni.getStorage({
			key: "signInID",
			success: (res) => {
				console.log(res.data);
				this.signInID = res.data;
			},
		});
  },
  mounted() {
    this.getLeaveList();
    this.getIsSignInList();
  },
  methods: {
    //查询请假人员
    getLeaveList() {
      uni.request({
        url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/getLeaveStudent",
        method: "GET",
        data: {
          teacherName: this.username,
        },
        success: (res) => {
          console.log(res.data);
          this.leaveList = res.data;
        },
      });
    },
    //查询已签到人员
    getIsSignInList() {
      uni.request({
        url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/searchSignInStudent",
        method: "GET",
        data: {
          sid: this.signInID,
        },
        success: (res) => {
          console.log(res.data);
          this.signInList = res.data;
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  padding: 0 15px;
  .isSignIn {
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: #f8f9fd;
    border: 1px solid #ebeef3;
    font-size: 14px;
    color: $uni-text-color-grey;
    border-radius: 5px;
    margin-bottom: 10px;
  }
  .isLeave {
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: #f0ffec;
    border: 1px solid #b4e1b8;
    font-size: 14px;
    color: #6ab161;
    border-radius: 5px;
    margin-bottom: 10px;
  }
  .unSignIn {
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: #feeeef;
    border: 1px solid #edd9e5;
    font-size: 14px;
    color: #f980a3;
    border-radius: 5px;
    margin-bottom: 10px;
  }
}
</style>
