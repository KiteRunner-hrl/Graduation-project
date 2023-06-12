<!--
 * @Author: HuRuiLin
 * @Date: 2023-03-27 14:00:42
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 02:43:03
-->
<template>
	<view class="signInStatus-conatiner">
		<view class="signInStatus-lessonName">
			<p>当前课程：{{ lessonName }}</p>
		</view>
		<view class="circle">
			<p>签到人数：{{ signInNumber + "人" }}</p>
			<van-count-down :time="signInTime" format="mm 分 ss 秒" @finish="finished" style="color: #ffffff;" />
		</view>
		<view class="btn_toSignIn">
			<van-button type="primary" icon="smile" custom-style="width: 160px; border-radius: 6px" v-if="role == 'user'"
				@click="skipPage">去签到</van-button>
			<van-button type="primary" custom-style="width: 160px; border-radius: 6px" v-if="role == 'teacher'"
				@click="skipPage">查看详情</van-button>
		</view>
	</view>
</template>

<script>
	export default {
		props: ["lessonName", "signInNumber"],
		data() {
			return {
				signInTime: 0,
				signInID: 0,
				classId: 0,
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
			uni.getStorage({
				key: "classId",
				success: (res) => {
					this.classId = res.data;
				},
			});
			uni.getStorage({
				key: "signInID",
				success: (res) => {
					console.log(res.data);
					this.signInID = res.data;
				},
			});
			setTimeout(() => {
				this.getSignInList();
			}, 100);
		},
		// mounted() {
		// 	this.getSignInList();
		// },
		methods: {
			finished() {
				console.log("签到结束");
				//删除签到
				this.deleteSignIn();
				setTimeout(() => {
					this.getSignInList();
				});
				uni.removeStorage({
					key: "time",
					success: function(res) {
						console.log("删除时间");
					},
				});
			},
			//获取签到时间
			getSignInList() {
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/student/searchSignIn",
					methods: "GET",
					data: {
						classId: this.classId,
					},
					success: (res) => {
						console.log(res.data);
						for (let i = 0; i < res.data.length; i++) {
							this.signInTime = res.data[i].time * 60 * 1000;
						}
					}
				});
			},
			deleteSignIn() {
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/deleteSignIn",
					method: "GET",
					data: {
						id: this.signInID,
					},
					success: (res) => {
						console.log(res.data);
					},
				});
			},
			skipPage() {
				if (this.role == "teacher") {
					uni.navigateTo({
						url: "/pages/SignInParticulars/SignInHistory",
					});
				} else if (this.role == "user") {
					uni.navigateTo({
						url: "/pages/Index/FaceSignIn/FaceSignIn",
					});
				}
			},
		},
	};
</script>

<style lang="scss" scoped>
	.signInStatus-conatiner {
		display: flex;
		flex-direction: column;

		.signInStatus-lessonName {
			padding-top: 20px;
			text-align: start;
		}

		.circle {
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			align-self: center;
			width: 11em;
			height: 11em;
			margin-top: 15%;
			background-color: #266efe;
			clip-path: circle(50%);
			text-align: center;
			color: $uni-text-color-inverse;
			border: 6px solid #dbe7ff;
			border-radius: 50%;

			:nth-child(2) {
				margin-top: 20px;
			}
		}

		.btn_toSignIn {
			display: flex;
			justify-content: center;
			align-self: center;
			margin-top: 30px;
		}
	}
</style>