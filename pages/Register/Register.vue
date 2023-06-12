<template>
	<view class="tRegister-container">
		<p class="tRegister-title">注册新账户</p>
		<form @submit="onSubmit">
			<view class="form-border">
				<input class="form-input" v-model="fullName" placeholder-style="color: #c0c0c0;" placeholder="请输入姓名" />
			</view>
			<view class="form-border">
				<input class="form-input" v-model="username" placeholder-style="color: #c0c0c0;" placeholder="请输入学号或教师工号" />
			</view>
			<view class="form-border">
				<input class="form-input" v-model="password" password placeholder-style="color: #c0c0c0;" placeholder="请设置密码" />
			</view>
			<van-button form-type="submit" block>下一步</van-button>
		</form>
	</view>
</template>

<script>
	import Toast from "../../wxcomponents/@vant/weapp/dist/toast/toast.js";
	export default {
		name: "Register",
		data() {
			return {
				fullName: "",
				username: "",
				password: "",
			};
		},
		methods: {
			onSubmit() {
				if (this.username == "" && this.password == "" && this.fullName == "") {
					Toast.fail("输入不能为空");
				} else {
					this.register(this.fullName, this.username, this.password);
				}
			},
			register(fullName, username, password) {
				uni.navigateTo({
					url: `/pages/Register/CreateFace?fullName=${fullName}&username=${username}&password=${password}`,
				});
				// uni.request({
				//   url: "https://api-cn.faceplusplus.com/facepp/v3/faceset/create", //请求接口
				//   method: "post",
				//   data: {
				//     api_key: "", //填写创建的api_key
				//     api_secret: "", //填写的api_secret
				//     outer_id: "1334826870", //账号下的 FaceSet
				//   },
				//   header: {
				//     "content-type": "application/x-www-form-urlencoded",
				//   },
				//   success(res) {
				//     console.log(res.data); //打印
				//   },
				// });
				// await request({
				//   url: "/api/user/register",
				//   method: "POST",
				//   headers: {
				//     "Content-Type": "application/x-www-form-urlencoded",
				//   },
				//   params: {
				//     fullName: this.fullName,
				//     username: this.username,
				//     password: this.password,
				//   },
				// })
				//   .then((res) => {
				//     console.log(res);
				//     if (res.data.status == "200") {
				//       Toast.loading({
				//         message: "加载中...",
				//         forbidClick: true,
				//         loadingType: "spinner",
				//       });
				//     } else if (res.data.status == "202") {
				//       Toast.fail("用户已存在");
				//     }
				//   })
				//   .catch((err) => {
				//     console.log(err);
				//   });
			},
		},
	};
</script>

<style lang="scss" scoped>
	.tRegister-container {
		padding: 35px;

		.form-input {
			padding: 6px 2px;
			font-size: $uni-font-size-base;
		}

		.tRegister-title {
			font-size: 27px;
			margin-bottom: 50px;
		}
	}

	.form-border {
		padding: 0 5px;
		border: 1px solid $uni-border-color;
		border-radius: $uni-border-radius-base;
		margin-top: 25px;
	}

	/deep/ .van-button {
		background-color: $uni-color-primary;
		color: $uni-text-color-inverse;
		border-radius: $uni-border-radius-base;
		margin: 30px 0;
	}
</style>