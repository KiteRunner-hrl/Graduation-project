<!--
 * @Author: HuRuiLin
 * @Date: 2023-03-27 19:31:57
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 18:49:39
-->
<template>
	<view class="container">
		<viwe class="person-container">
			<view style="display: flex">
				<view class="head-container">
					<img src="../../../static/images/image-header.png" alt="" srcset="" />
				</view>
				<view class="userMessage-container">
					<p class="userName-text">{{ username }}</p>
					<p class="userNumber-text">账号：{{ code }}</p>
				</view>
			</view>
			<view @click="showJoinClass"><van-icon name="friends-o" size="26px" /></view>
		</viwe>
		<van-dialog use-slot title="输入班级邀请码" :show="show" bind:close="onClose" bind:getuserinfo="getUserInfo"
			:showConfirmButton="false">
			<view style="padding: 0 15px">
				<view class="input-container">
					<view class="form-border"><input v-model="classCode" class="form-input" placeholder-style="color: #c0c0c0;"
							placeholder="请输入班级邀请码" /></view>
				</view>
				<van-button block color="#0256ff" custom-style="margin: 30px 0; border-radius: 5px"
					@click="joinClass()">提交</van-button>
			</view>
		</van-dialog>
		<van-toast id="van-toast" />
	</view>
</template>

<script>
	import Toast from "../../../wxcomponents/@vant/weapp/dist/toast/toast";
	export default {
		data() {
			return {
				show: false,
				username: "",
				code: 0,
				classCode: "",
				userId: 0
			};
		},
		created() {
			uni.getStorage({
				key: "fullName",
				success: (res) => {
					console.log(res.data);
					this.username = res.data;
				},
			});
			uni.getStorage({
				key: "usercode",
				success: (res) => {
					console.log(res.data);
					this.code = res.data;
				},
			});
			uni.getStorage({
				key: "userId",
				success: (res) => {
					console.log(res.data);
					this.userId = res.data;
				}
			})
		},
		methods: {
			showJoinClass() {
				this.show = true;
			},
			onClose() {
				this.show = false;
			},
			joinClass() {
				console.log(this.code);
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/student/joinClass",
					method: "POST",
					data: {
						classCode: this.classCode,
						userId: this.userId,
					},
					header: {
						"content-type": "application/x-www-form-urlencoded;charset=utf-8",
					},
					success: (res) => {
						console.log(res.data);
						if (res.data.status == "200") {
							this.show = false;
							Toast.success("加入成功");
						}
					},
				});
			},
		},
	};
</script>

<style lang="scss" scoped>
	.container {
		.input-container {
			padding: 10px 15px;

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

		.person-container {
			display: flex;
			height: 80px;
			padding-left: 15px;
			padding-right: 30px;
			margin-bottom: 15px;
			border-bottom: 5px solid #f9f9f9;
			justify-content: space-between;
			align-items: center;

			.head-container {
				img {
					width: 60px;
					height: 60px;
				}
			}

			.userMessage-container {
				padding: 0 15px;

				.userName-text {
					font-size: 18px;
					font-weight: bold;
					margin-top: 6px;
					margin-bottom: 7px;
				}

				.userNumber-text {
					color: #9e9e9e;
					font-size: 14px;
				}
			}
		}
	}
</style>