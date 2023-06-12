<!--
 * @Author: HuRuiLin
 * @Date: 2023-04-09 21:13:42
 * @LastEditor: HuRuilin
 * @LastEditTime: 2023-05-10 01:42:47
-->
<template>
	<view class="container">
		<view class="form-container">
			<view class="input-container">
				<p class="title">考勤课程</p>
				<view class="form-border">
					<input class="form-input" v-model="lessonName" placeholder-style="color: #c0c0c0;"
						placeholder="请输入班级名称如：(大学英语III)" />
				</view>
			</view>
			<view class="input-container" @click="selectTime">
				<p class="title">设置时间</p>
				<view class="select-border">
					<p style="color: #c0c0c0" v-if="isSelectTime == false">
						设置签到时间
					</p>
					<p style="color: #000000" v-if="isSelectTime == true">
						{{ currentDate }}
					</p>
					<van-icon name="underway-o" size="16px" />
				</view>
			</view>
			<view class="btn-container">
				<van-button color="#0256ff" block custom-style="margin-top: 50px; border-radius: 5px;"
					@click="createSignIn">创建</van-button>
			</view>
		</view>
		<van-action-sheet :show="shows" title="选择签到时限" @close="onClose" @click-overlay="onClose">
			<view style="padding: 0 10px">
				<van-datetime-picker type="time" title="分钟" visible-item-count="5" :min-hour="minHour" :max-hour="maxHour"
					:min-minute="minMinute" :max-minute="maxMinute" @confirm="showTime" @input="onInput" />
			</view>
		</van-action-sheet>
		<van-toast id="van-toast" />
	</view>
</template>

<script>
	import Toast from "../../../wxcomponents/@vant/weapp/dist/toast/toast.js";
	export default {
		data() {
			return {
				shows: false,
				isSelectTime: false,
				isSelectClass: false,
				lessonName: "",
				className: "请选择考勤班级",
				minMinute: 2,
				time: 0,
				userId: 0,
				classId: 0,
				maxMinute: 20,
				minHour: 0,
				maxHour: 0,
				currentDate: "",
			};
		},
		created() {
			uni.getStorage({
				key: "userId",
				success: (res) => {
					this.userId = res.data;
				},
			});
			uni.getStorage({
				key: "classId",
				success: (res) => {
					this.classId = res.data;
				},
			});
		},
		methods: {
			selectTime() {
				this.shows = true;
			},
			onCloseTime() {
				this.shows = false;
			},
			onClose() {
				this.shows = false;
			},
			onInput(event) {
				this.currentDate = event.detail;
			},
			showTime() {
				this.isSelectTime = true;
				let timer = this.currentDate;
				let timerArr = timer.split(":");
				let minute = parseInt(timerArr[1]);
				this.time = minute;
				console.log(minute);
				this.shows = false;
			},
			onSelect(event) {
				this.isSelectClass = true;
				this.className = event.detail.name;
				console.log(event.detail.name);
			},
			//创建考勤
			createSignIn() {
				Toast.loading({
					message: "创建中...",
					forbidClick: true,
				});
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/teacher/createSignIn",
					method: "POST",
					data: {
						teacherId: this.userId,
						classId: this.classId,
						lessonName: this.lessonName,
						time: this.time
					},
					header: {
						"content-type": "application/x-www-form-urlencoded;charset=utf-8",
					},
					success: (res) => {
						console.log(res.data);
						Toast.clear();
						Toast.success("创建成功");
						// uni.setStorage({
						// 	key: "time",
						// 	data: this.time
						// });
						uni.navigateTo({
							url: "/pages/Index/SignInList/SignInList",
						});
					},
				});
			},
		},
	};
</script>

<style lang="scss" scoped>
	.container {
		padding: 10px 15px;

		.form-container {
			.title {
				font-weight: 700;
			}

			.input-container {
				margin-bottom: 18px;

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

				.form-border {
					padding: 0 5px;
					border: 1px solid $uni-border-color;
					border-radius: $uni-border-radius-base;
					margin-top: 10px;

					.form-input {
						padding: 6px 2px;
						font-size: $uni-font-size-base;
					}
				}
			}
		}
	}
</style>