<!--
 * @Author: HuRuiLin
 * @Date: 2023-03-25 15:29:30
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 16:08:59
-->
<template>
	<view class="index-container">
		<!-- 首页用户名展示 -->
		<view class="index-username" style="margin-bottom: 20px">
			<p>HI, {{ username }}</p>
		</view>
		<!-- 首页课程展示 -->
		<view class="index-lesson-box">
			<!-- 首页签到展示 -->
			<div class="index-signIn-title">
				<view class="signIn-title">
					<span>课程考勤</span>
				</view>
				<div class="signInRecordEnter" @click="skipPages()">
					查看详情
					<van-icon name="arrow" />
				</div>
			</div>
			<view class="index-signIn-list" @click="toSignInList">
				<LessonList v-for="item in classList" :key="item.index" :className="item.className" :classNum="item.classNum"
					:teacher="item.teacherName"></LessonList>
			</view>
		</view>
		<Empty_user :emptyContent="emptyContent" v-if="(classList.length == 0) & (role == 'user')"></Empty_user>
	</view>
</template>

<script>
	import Lesson from "../../components/Lesson/Lesson.vue";
	import Empty from "../../components/Empty/Empty.vue";
	import Empty_user from "../../components/Empty/Empty_user.vue";
	import LessonList from "../Index/components/LessonList/LessonList.vue";

	export default {
		name: "Index",
		components: {
			Lesson,
			Empty,
			Empty_user,
			LessonList,
		},
		data() {
			return {
				signInStart: true,
				show: false,
				signInStatus: {
					isSignIn: "已签到",
					waitSignIn: "待签到",
				},
				username: "",
				id: "",
				role: "",
				classId: 0,
				signInNumber: "100",
				emptyContent: "暂无班级数据",
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
			this.getClassList();
		},
		methods: {
			skipPages() {
				uni.navigateTo({
					url: "/pages/SignInParticulars/SignInParticulars",
				});
			},
			toSignInList() {
				uni.navigateTo({
					url: "/pages/Index/SignInList/SignInList",
				});
			},
			//请求签到活动列表

			//请求班级列表
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
								uni.setStorage({
									key: "classId",
									data: res.data[i].id
								});
							}
						},
					});
				} else if (this.role == "user") {
					console.log("根据加入班级的ID请求相应的班级");
					uni.request({
						url: "https://www.huruilinweb.top/KbdBackEnd/api/student/classList",
						method: "GET",
						data: {
							id: this.classId,
						},
						success: (res) => {
							console.log(res.data);
							this.classList = res.data;
						}
					})
				}
			},
		},
	};
</script>

<style lang="scss" scoped>
	.index-container {
		padding: 20px;

		.index-signIn-title {
			display: flex;
			align-items: center;
			justify-content: space-between;
			margin-bottom: 25px;

			.signIn-title {
				display: inline-block;
				width: auto;
				border-bottom: 3px solid #0256ff;
				font-size: 19px;
				font-weight: 700;
				font-family: Arial, Helvetica, sans-serif;
			}
		}

		.index-common-title {
			display: inline-block;
			width: auto;
			border-bottom: 3px solid #0256ff;
			font-size: 19px;
			font-weight: 700;
			font-family: Arial, Helvetica, sans-serif;
			margin-bottom: 25px;
		}

		.signInRecordEnter {
			font-size: 14px;
			color: $uni-text-color-grey;
		}
	}
</style>