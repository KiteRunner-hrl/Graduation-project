<!--
 * @Author: HuRuiLin
 * @Date: 2023-03-30 21:49:32
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 22:02:02
-->
<template>
  <view class="classList-container">
    <van-button
      block
      color="#0256ff"
      custom-style="margin-bottom: 20px"
      icon="plus"
      @click="showDialog"
      >创建班级</van-button
    >
    <van-cell-group>
      <van-cell
        is-link
        center
        v-for="item in classList"
        :key="item.index"
        @click="
          () => skipPage(item.className, item.id, item.classNum, item.classCode)
        "
      >
        <view bind:slot="title" class="common-enter">
          <van-tag type="success" size="medium">班级</van-tag>
          <view class="van-cell-text">{{ item.className }}</view>
        </view>
      </van-cell>
    </van-cell-group>
    <view>
      <van-dialog
        use-slot
        title="创建班级"
        :show="show"
        confirm-button-color="#0256ff"
        show-cancel-button
        @cancel="onClose"
        @confirm="onConFirm"
      >
        <view class="input-container">
          <view class="form-border"
            ><input
              v-model="className"
              class="form-input"
              placeholder-style="color: #c0c0c0;"
              placeholder="请输入班级名称"
          /></view>
        </view>
      </van-dialog>
    </view>
    <van-toast id="van-toast" />
  </view>
</template>

<script>
import Toast from "../../../wxcomponents/@vant/weapp/dist/toast/toast";
export default {
  data() {
    return {
      className: "",
      show: false,
      username: "",
      id: 0,
      role: "",
      toClassAdmin: "",
      classList: [],
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
    uni.getStorage({
      key: "role",
      success: (res) => {
        this.role = res.data;
      },
    });
  },
  mounted() {
    this.getClassList();
  },
  methods: {
    skipPage(className, classId, classNum, classCode) {
      uni.navigateTo({
        url: `/pages/workPlace/ClassAdmin/classAdmin?className=${className}&classId=${classId}&classNum=${classNum}&classCode=${classCode}`,
      });
    },
    showDialog() {
      this.show = true;
    },
    onClose() {
      this.show = false;
    },
    onConFirm() {
      this.show = false;
      console.log("确认");
      this.createClass();
    },
    getClassList() {
      if (this.role == "teacher") {
        uni.request({
          url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/searchAllClass",
          methods: "GET",
          data: {
            teacherId: this.id,
          },
          success: (res) => {
            console.log(res.data);
            this.classList = res.data;
            for (let i = 0; i < res.data.length; i++) {
              this.toClassAdmin = res.data[i].className;
            }
          },
        });
      } else if (this.role == "user") {
        console.log("根据加入班级的ID请求相应的班级");
      }
    },
    createClass() {
      Toast.loading({
        message: "创建中...",
        forbidClick: true,
      });
      uni.request({
        url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/createClass",
        method: "POST",
        data: {
          className: this.className,
          teacherId: this.id,
          teacherName: this.username,
        },
        header: {
          "content-type": "application/x-www-form-urlencoded;charset=utf-8",
        },
        success: (res) => {
          Toast.clear();
          console.log(res.data);
          Toast.success("创建成功");
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.classList-container {
  padding: 10px 0;
  height: 100%;
  .common-enter {
    display: flex;
    align-items: center;
    .van-cell-text {
      color: black;
      margin-left: 15px;
      font-size: 15px;
    }
  }
  .input-container {
    margin-bottom: 25px;
    padding: 0 15px;
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
  }
}
/deep/ .van-hairline--top-bottom:after {
  border: 0;
}
</style>
