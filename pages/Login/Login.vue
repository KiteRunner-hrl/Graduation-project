<!--
 * @Author: HuRuiLin
 * @Date: 2023-02-21 18:39:25
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-22 22:46:16
-->
<template>
	<view class="login-container">
		<p class="login-title">HI, 欢迎登录</p>
		<view class="login-form-container">
			<form @submit="onSubmit">
				<view class="form-border">
					<input class="form-input" v-model="username" placeholder-style="color: #c0c0c0;" placeholder="请输入用户名" />
				</view>
				<view class="form-border">
					<input class="form-input" v-model="password" password placeholder-style="color: #c0c0c0;"
						placeholder="请输入密码" />
				</view>
				<van-button form-type="submit" block>登 录</van-button>
			</form>
			<view class="register-container">
				<view style="display: flex" @click="gotoRegister">
					还没有账号？
					<p class="btn-register">马上注册</p>
				</view>
			</view>
		</view>
		<van-toast id="van-toast" />
		<van-dialog id="van-dialog" />
	</view>
</template>

<script>
	import Toast from "../../wxcomponents/@vant/weapp/dist/toast/toast";
	import Dialog from "../../wxcomponents/@vant/weapp/dist/dialog/dialog";
	export default {
		name: "Login",
		data() {
			return {
				username: "",
				password: "",
				face_token: "",
			};
		},
		methods: {
			//登录表单提交
			onSubmit() {
				if (this.username == "" && this.password == "") {
					Toast.fail("输入不能为空");
				} else {
					this.login();
				}
			},
			//登录请求
			login() {
				Toast.loading({
					message: "正在登录...",
					forbidClick: true,
				});
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/user/login",
					method: "POST",
					data: {
						username: this.username,
						password: this.password,
					},
					header: {
						"content-type": "application/x-www-form-urlencoded;charset=utf-8",
					},
					success: (res) => {
						console.log(res.data);
						// this.face_token = res.data.face_token;
						// face_token = this.face_token;
						Toast.clear();
						if (res.data.status == "200") {
							uni.setStorage({
								key: "userId",
								data: res.data.userId
							});
							uni.setStorage({
								key: "fullName",
								data: res.data.fullName
							});
							uni.setStorage({
								key: "role",
								data: res.data.role
							});
							uni.setStorage({
								key: "usercode",
								data: res.data.username
							});
							uni.setStorage({
								key: "classId",
								data: res.data.classId
							});
							uni.setStorage({
								key: "face_token",
								data: res.data.face_token
							});
							Toast.success("登录成功");
							// uni.navigateTo({
							// 	// url: `/pages/Index/FaceSignIn/FaceSignIn?face_token=${face_token}`,
							// 	url: ""
							// });
							uni.switchTab({
								url: "/pages/Index/Index"
							})
						} else {
							if (res.data.status == "202") {
								Dialog.alert({
									message: "用户名或密码有误",
									theme: "round-button",
								}).then(() => {
									// on close
								});
							}
						}
					},
				});
			},
			//跳转注册页
			gotoRegister() {
				uni.navigateTo({
					url: "/pages/Register/Register",
				});
			},
		},
	};
</script>

<style lang="scss" scoped>
	.login-container {
		padding: 30px;

		.login-title {
			font-size: 27px;
			margin-bottom: 50px;
		}

		.login-form-container {
			.form-input {
				padding: 6px 2px;
				font-size: $uni-font-size-base;
			}

			.forgetPassword-container {
				color: $uni-text-color-grey;
				margin-top: 7px;
				font-size: $uni-font-size-base;
			}

			.register-container {
				display: flex;
				justify-content: center;
				color: $uni-text-color-grey;
				align-items: center;
				font-size: $uni-font-size-sm;

				.btn-register,
				.btn-toForgetPassword {
					color: $uni-color-primary;
				}
			}

			/deep/ .van-button {
				background-color: $uni-color-primary;
				color: $uni-text-color-inverse;
				border-radius: $uni-border-radius-base;
				margin: 30px 0;
			}
		}
	}

	// 登录表单自定义样式
	/deep/ .van-cell {
		--cell-vertical-padding: 5px !important;
		--cell-horizontal-padding: 2px;
	}

	.form-border {
		padding: 0 5px;
		border: 1px solid $uni-border-color;
		border-radius: $uni-border-radius-base;
		margin-top: 25px;
	}
</style>