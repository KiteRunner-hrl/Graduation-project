<!--
 * @Author: HuRuiLin
 * @Date: 2023-05-03 18:48:01
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-22 22:33:56
-->
<template>
  <view class="container">
    <camera
      device-position="front"
      flash="off"
      @error="error"
      style="width: 100%; height: 350px"
    ></camera>
    <view class="tip-container">
      <van-button block @click="registerFace">拍照上传</van-button>
    </view>
    <van-toast id="van-toast" />
  </view>
</template>

<script>
import Toast from "../../wxcomponents/@vant/weapp/dist/toast/toast.js";
export default {
  data() {
    return {
      fullName: "",
      username: "",
      password: "",
      face_token: "",
      confidence: 0,
      src: "",
    };
  },
  onLoad: function (options) {
    console.log(options);
    this.fullName = options.fullName;
    this.username = options.username;
    this.password = options.password;
  },
  methods: {
    //人脸注册
    registerFace() {
      console.log("1234");
      Toast.loading({
        message: "正在上传...",
        forbidClick: true,
      });
      //创建camera上下文
      const ctx = uni.createCameraContext();
      //拍照获取图片
      ctx.takePhoto({
        quality: "high",
        success: (res) => {
          this.src = res.tempImagePath;
          //上传图片文件到接口
          uni.uploadFile({
            url: "https://api-cn.faceplusplus.com/facepp/v3/detect",
            filePath: this.src,
            name: "image_file",
            formData: {
              api_key: "tmjzhJZqDRRCoUk1UXbH8IZPYr002EWI",
              api_secret: "p6LBqyYqjBhOysEVFE7pGpUY43v5OWdh",
            },
            header: {
              "Content-Type": "multipart/form-data",
            },
            success: (res) => {
              console.log(res.data);
              let obj = JSON.parse(res.data);
              if (obj["faces"][0] == null || obj["faces"][0] == "") {
                //Toast获取不到人脸
                Toast.fail("获取不到人脸");
              } else {
                this.face_token = obj["faces"][0]["face_token"]; //获取到人脸标识
                this.addFace();
              }
            },
          });
        },
      });
    },
    addFace() {
      uni.request({
        url: "https://api-cn.faceplusplus.com/facepp/v3/faceset/addface",
        method: "POST",
        data: {
          api_key: "tmjzhJZqDRRCoUk1UXbH8IZPYr002EWI",
          api_secret: "p6LBqyYqjBhOysEVFE7pGpUY43v5OWdh",
          face_tokens: this.face_token,
          outer_id: "1334826870",
        },
        header: {
          "content-type": "application/x-www-form-urlencoded",
        },
        success: (res) => {
          console.log(res.data);
          //提交注册信息
          this.submitRegister();
        },
      });
    },
    submitRegister() {
      uni.request({
        url: "https://www.huruilinweb.top/KbdBackEnd/api/user/register",
        method: "POST",
        data: {
          fullName: this.fullName,
          username: this.username,
          password: this.password,
          face_token: this.face_token,
        },
        header: {
          "content-type": "application/x-www-form-urlencoded;charset=utf-8",
        },
        success: (res) => {
          console.log(res.data);
          if (res.data.status == "200") {
            Toast.clear();
            setTimeout(() => {
              uni.navigateTo({
                url: "/pages/Register/success/Success",
              });
            }, 500);
          } else if (res.data.status == "202") {
            Toast.fail("用户已存在");
          }
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  .tip-container {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 15px 0;
  }
}

/deep/ .van-button {
  width: 150px;
  height: 150px;
  background-color: $uni-color-primary;
  color: $uni-text-color-inverse;
  border-radius: $uni-border-radius-base;
  margin: 30px 0;
  border-radius: 50%;
  font-size: 18px;
}
</style>
