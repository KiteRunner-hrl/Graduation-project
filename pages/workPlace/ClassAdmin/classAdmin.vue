<!--
 * @Author: HuRuiLin
 * @Date: 2023-03-31 11:58:17
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 22:21:51
-->
<template>
	<view>
		<view class="classAdmin-container">
			<view class="classAdmin-title">
				<p class="studentCount">班级人数:{{ studentCount }}人</p>
				<p class="className">{{ className }}</p>
				<view class="admin-enter" @click="deleteClass()"><van-icon name="delete" size="19px" /></view>
			</view>
			<view class="classCode_container">
				<p>班级邀请码：{{ classCode }}</p>
			</view>
			<view class="classAdmin-body" v-for="item in studentList" :key="item.index">
				<ClassMemberList :type="item.role" :name="item.fullName"></ClassMemberList>
			</view>
			<Empty_user :emptyContent="emptyContent" v-if="studentList.length == 0"></Empty_user>
			<van-toast id="van-toast" />
			<van-dialog id="van-dialog" />
		</view>
	</view>
</template>

<script>
	import Toast from "../../../wxcomponents/@vant/weapp/dist/toast/toast";
	import ClassMemberList from "../components/classMemberList.vue";
	import Empty_user from "../../../components/Empty/Empty_user.vue";
	import Dialog from "../../../wxcomponents/@vant/weapp/dist/dialog/dialog";
	export default {
		components: {
			ClassMemberList,
			Empty_user
		},
		data() {
			return {
				className: "",
				studentCount: "",
				classId: 0,
				classCode: "",
				emptyContent: "暂无班级成员",
				username: "张三",
				userType: "学生",
				studentList: [],
			};
		},
		onLoad: function(options) {
			console.log(options);
			this.studentCount = options.classNum;
			this.className = options.className;
			this.classId = options.classId;
			this.classCode = options.classCode;
		},
		mounted() {
			this.getClassStudent();
		},
		methods: {
			//根据ID查看班级里的学生
			getClassStudent() {
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/classDetail",
					method: "GET",
					data: {
						id: this.classId,
					},
					success: (res) => {
						console.log(res.data);
						this.studentList = res.data;
					},
				});
			},
			//解散班级
			deleteClass() {
				Dialog.alert({
					message: "确定删除该班级？",
					theme: "round-button",
				}).then(() => {
					// on close
					uni.request({
						url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/deleteClass",
						method: "POST",
						data: {
							classID: this.classId,
						},
						header: {
							"content-type": "application/x-www-form-urlencoded;charset=utf-8",
						},
						success: (res) => {
							console.log(res.data);
							if (res.data.status == "200") {
								Toast.success("删除成功");
								uni.navigateTo({
									url: "/pages/workPlace/ClassAdmin/classList",
								});
							}
						},
					});
				});
			},
		},
	};
</script>

<style lang="scss" scoped>
	.classAdmin-container {
		.classAdmin-title {
			height: 50px;
			display: flex;
			padding: 0 15px;
			align-items: center;
			justify-content: space-between;

			.studentCount {
				flex: 1;
				font-size: 12px;
				color: $uni-text-color-grey;
			}

			.className {
				flex: 2;
				text-align: center;
			}

			.admin-enter {
				flex: 1;
				text-align: end;
			}
		}

		.classCode_container {
			height: 45px;
			background-color: $uni-color-primary;
			color: white;
			text-align: center;
			line-height: 45px;
		}
	}
</style>