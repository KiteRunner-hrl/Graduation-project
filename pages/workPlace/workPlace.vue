<!--
 * @Author: HuRuiLin
 * @Date: 2023-03-25 15:25:14
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 17:11:32
-->
<template>
  <view class="workPlace-container">
    <Person></Person>
    <van-cell-group>
      <van-cell
        is-link
        center
        link-type="navigateTo"
        url="./LeaveRequest/LeaveRequest"
      >
        <view bind:slot="title" class="common-enter">
          <img src="../../static/icon/icon-leave.png" alt="" srcset="" />
          <view class="van-cell-text">请假管理</view>
        </view>
      </van-cell>
      <!-- <van-cell is-link border="false" center>
        <view bind:slot="title" class="common-enter">
          <img src="../../static/icon/icon-lesson.png" alt="" srcset="" />
          <view class="van-cell-text">课程管理</view>
        </view>
      </van-cell> -->
      <van-cell
        is-link
        center
        link-type="navigateTo"
        url="./PersonAdmin/personAdmin"
        v-if="role == 'admin'"
      >
        <view bind:slot="title" class="common-enter">
          <img src="../../static/icon/icon-person.png" alt="" srcset="" />
          <view class="van-cell-text">人员管理</view>
        </view>
      </van-cell>
      <van-cell
        is-link
        center
        link-type="navigateTo"
        url="./ClassAdmin/classList"
        v-if="role == 'teacher'"
      >
        <view bind:slot="title" class="common-enter">
          <img src="../../static/icon/icon-class.png" alt="" srcset="" />
          <view class="van-cell-text">班级管理</view>
        </view>
      </van-cell>
      <van-cell is-link center>
        <view bind:slot="title" class="common-enter">
          <img src="../../static/icon/icon-about.png" alt="" srcset="" />
          <view class="van-cell-text">关于我们</view>
        </view>
      </van-cell>
    </van-cell-group>
    <view style="text-align: center">
      <van-button
        color="#E0EAFF"
        custom-style="margin-top: 30px; border-radius: 5px; width: 200px; color: #0256ff"
        @click="exitLogin"
        >退出登录</van-button
      >
    </view>
    <van-dialog id="van-dialog" />
  </view>
</template>

<script>
import Person from "./components/Person.vue";
import Dialog from "../../wxcomponents/@vant/weapp/dist/dialog/dialog";
export default {
  components: {
    Person,
  },
  data() {
    return {
      show: false,
      role: "",
    };
  },
  created() {
    uni.getStorage({
      key: "role",
      success: (res) => {
        this.role = res.data;
      },
    });
  },
  methods: {
    exitLogin() {
      Dialog.confirm({
        message: "退出登录？",
      })
        .then(() => {
					uni.clearStorage();
          uni.navigateTo({
            url: "/pages/Login/Login",
          });
        })
        .catch(() => {
          // on cancel
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.workPlace-container {
  .common-enter {
    display: flex;
    align-items: center;

    .van-cell-text {
      color: black;
      margin-left: 15px;
      font-size: 15px;
    }

    img {
      width: 35px;
      height: 35px;
    }
  }
}

/deep/ .van-cell:after {
  border: 0;
}

/deep/ .van-hairline--top-bottom:after {
  border: 0;
}
</style>
