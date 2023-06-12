<!--
 * @Author: HuRuiLin
 * @Date: 2023-04-05 19:58:58
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-14 01:56:02
-->
<template>
	<view>
		<view class="container" v-for="(item, index) in leaveList" :key="index">
			<view class="header">
				<view class="left-container">
					<p>{{ item.userName }}的请假</p>
				</view>
				<p style="color: #ff8c00" v-if="item.status == 0">待审批</p>
				<p style="color: #57a851" v-if="item.status == 1">已通过</p>
				<p style="color: #ea0000" v-if="item.status == 2">未通过</p>
			</view>
			<view class="body">
				<p>请假课程：{{ item.lessonName }}</p>
				<p>请假类型：{{ item.leaveType }}</p>
			</view>
			<view class="bottom">
				<p>请假事由：{{ item.leaveReason }}</p>
				<p style="color: #ea0000" v-if="item.status == 2">
					拒绝原因：{{ item.reject }}
				</p>
				<view v-if="item.status == 0 && role == 'teacher'">
					<van-button type="primary" size="small" custom-style="margin-right: 10px; border-radius: 5px"
						@click="leaveApprovalPass(item.id)">同意</van-button>
					<van-button type="danger" size="small" custom-style="border-radius: 5px" @click="openDialog">不同意</van-button>
				</view>
			</view>
			<van-dialog use-slot title="填写拒绝原因" :show="show" @close="close" @getuserinfo="getUserInfo"
				:showConfirmButton="false" :close-on-click-overlay="true">
				<view class="input-container">
					<view class="form-border">
						<textarea v-model="failMessage" placeholder="请输入拒绝原因" placeholder-style="color: #c0c0c0;"
							style="width: 100%; margin: 6px 0"></textarea>
					</view>
					<van-button block color="#0256ff" custom-style="margin: 30px 0; border-radius: 5px"
						@click="leaveApprovalReject(item.id)">提交</van-button>
				</view>
			</van-dialog>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				role: "",
				show: false,
				isClick: false,
				failMessage: "",
				fullName: "",
				leaveList: [],
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
				key: "fullName",
				success: (res) => {
					this.fullName = res.data;
				},
			});
		},
		mounted() {
			this.getLeaveList();
		},
		methods: {
			openDialog() {
				this.show = true;
			},
			close() {
				this.show = false;
			},
			isApproved() {
				this.approved = false;
				this.pass = true;
				this.isClick = true;
			},
			//根据审核教师查询请假列表
			getLeaveList() {
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/getLeaveByTeacherName",
					method: "GET",
					data: {
						teacherName: this.fullName,
					},
					success: (res) => {
						console.log(res.data);
						this.leaveList = res.data;
					},
				});
			},
			//获取人脸库信息
			getFaceSetMessage() {
				uni.request({
					url: "https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail",
					method: "POST",
					data: {
						api_key: "tmjzhJZqDRRCoUk1UXbH8IZPYr002EWI",
						api_secret: "p6LBqyYqjBhOysEVFE7pGpUY43v5OWdh",
						outer_id: "1334826870",
					},
					header: {
						"content-type": "application/x-www-form-urlencoded;charset=utf-8",
					},
					success: (res) => {
						const face_tokens = res.data.face_tokens
						this.deleteFace(face_tokens);
					},
				});
			},
			deleteFace(face_tokens) {
				uni.request({
					url: "https://api-cn.faceplusplus.com/facepp/v3/faceset/removeface",
					method: "POST",
					data: {
						api_key: "tmjzhJZqDRRCoUk1UXbH8IZPYr002EWI",
						api_secret: "p6LBqyYqjBhOysEVFE7pGpUY43v5OWdh",
						outer_id: "1334826870",
					},
					header: {
						"content-type": "application/x-www-form-urlencoded;charset=utf-8",
					},
					success: (res) => {
						console.log(res.data);
						this.getLeaveList();
					},
				});
			},
			//请假审批(同意)
			leaveApprovalPass(leaveId) {
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/leaveApprovalPass",
					method: "POST",
					data: {
						id: leaveId,
						status: 1,
					},
					header: {
						"content-type": "application/x-www-form-urlencoded;charset=utf-8",
					},
					success: (res) => {
						console.log(res.data);
						this.getLeaveList();
					},
				});
			},
			//请假审批(不同意)
			leaveApprovalReject(leaveId) {
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/leaveApprovalReject",
					method: "POST",
					data: {
						id: leaveId,
						status: 2,
						reject: this.failMessage,
					},
					header: {
						"content-type": "application/x-www-form-urlencoded;charset=utf-8",
					},
					success: (res) => {
						console.log(res.data);
						this.getLeaveList();
						this.show = false;
					},
				});
			},
		},
	};
</script>

<style lang="scss" scoped>
	.container {
		background-color: $uni-bg-color;
		border-radius: 5px;
		padding: 15px;
		margin-top: 10px;

		.header {
			display: flex;
			align-items: center;
			justify-content: space-between;

			.left-container {
				display: flex;
				align-items: center;

				p:nth-child(2) {
					font-size: 12px;
					margin-left: 10px;
					color: $uni-text-color-grey;
				}
			}
		}

		.body {
			margin-top: 10px;

			p {
				margin-bottom: 6px;
			}
		}

		.bottom {
			display: flex;
			align-items: center;
			justify-content: space-between;
		}

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
	}
</style>