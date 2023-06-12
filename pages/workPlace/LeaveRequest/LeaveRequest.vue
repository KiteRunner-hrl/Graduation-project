<!--
 * @Author: HuRuiLin
 * @Date: 2023-03-29 13:53:08
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 22:48:25
-->
<template>
  <view class="leave-container">
    <form @submit="onSubmit">
      <view class="input-container">
        <p class="title">课程名称</p>
        <view class="form-border"
          ><input
            class="form-input"
            v-model="formData.lessonName"
            placeholder-style="color: #c0c0c0;"
            placeholder="请输入课程名称"
        /></view>
      </view>
      <view class="input-container" @click="showSelect">
        <p class="title">请假类型</p>
        <view class="select-border">
          <p style="color: #c0c0c0" v-if="isSelect == false">
            {{ selectType }}
          </p>
          <p v-if="isSelect == true">{{ formData.leaveType }}</p>
          <van-icon name="arrow-down" />
        </view>
      </view>
      <view class="input-container">
        <p class="title">请假事由</p>
        <view class="form-border">
          <textarea
            v-model="formData.leaveReason"
            placeholder="请输入请假原因"
            placeholder-style="color: #c0c0c0;"
            style="width: 100%; margin: 6px 0"
          ></textarea>
        </view>
      </view>
      <view class="input-container">
        <p class="title">审核老师</p>
        <view class="form-border"
          ><input
            class="form-input"
            v-model="formData.auditTeacher"
            placeholder-style="color: #c0c0c0;"
            placeholder="请输入审核老师名字"
        /></view>
      </view>
      <van-button block color="#0256ff" @click="requestLeave">提交</van-button>
    </form>
    <van-button block type="danger" @click="skipPage()">查看审批</van-button>
    <van-action-sheet
      :show="show"
      :actions="actions"
      @close="onClose"
      @select="onSelect"
    />
    <van-toast id="van-toast" />
  </view>
</template>

<script>
import Toast from "../../../wxcomponents/@vant/weapp/dist/toast/toast";
export default {
  data() {
    return {
      show: false,
      isSelect: false,
      selectType: "选择请假类型",
      id: "",
      username: "",
      formData: {
        lessonName: "",
        leaveType: "",
        leaveReason: "",
        auditTeacher: "",
      },
      actions: [
        {
          name: "病假",
        },
        {
          name: "事假",
        },
      ],
    };
  },
  created() {
    uni.getStorage({
      key: "userId",
      success: (res) => {
        this.id = res.data;
      },
    });
    uni.getStorage({
      key: "fullName",
      success: (res) => {
        this.username = res.data;
      },
    });
  },
  methods: {
    showSelect() {
      this.show = true;
    },
    onClose() {
      this.show = false;
    },
    onSelect(event) {
      console.log(event.detail.name);
      this.formData.leaveType = event.detail.name;
      this.isSelect = true;
    },
    onSubmit() {
      this.requestLeave();
    },
    skipPage() {
      uni.navigateTo({
        url: "../LeaveAudit/LeaveAudit",
      });
    },
    requestLeave() {
      Toast.loading({
        message: "提交中...",
        forbidClick: true,
      });
      uni.request({
        url: "https://www.huruilinweb.top/KbdBackEnd/api/student/leaveRequest",
        method: "POST",
        data: {
          userId: this.id,
          fullName: this.username,
          lessonName: this.formData.lessonName,
          leaveType: this.formData.leaveType,
          leaveReason: this.formData.leaveReason,
          approveTeacher: this.formData.auditTeacher,
        },
        header: {
          "content-type": "application/x-www-form-urlencoded;charset=utf-8",
        },
        success: (res) => {
          Toast.clear();
          console.log(res.data);
          if (res.data.status == "200") {
            Toast.success("提交成功");
          }
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.leave-container {
  padding: 15px;
  .input-container {
    margin-bottom: 15px;
  }
  .form-border {
    padding: 0 5px;
    border: 1px solid $uni-border-color;
    border-radius: $uni-border-radius-base;
    margin-top: 10px;
  }
  .form-input {
    padding: 6px 2px;
    font-size: $uni-font-size-base;
  }
  .select-border {
    display: flex;
    align-items: center;
    height: 1.4rem;
    font-size: 14px;
    justify-content: space-between;
    padding: 6px 8px;
    border: 1px solid $uni-border-color;
    border-radius: $uni-border-radius-base;
    margin-top: 10px;
  }
}
/deep/ .van-cell {
  border: 0;
}
/deep/ .van-button {
  margin-top: 35px;
  border-radius: $uni-border-radius-base;
}
</style>
