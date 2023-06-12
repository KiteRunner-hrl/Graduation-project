<template>
	<view class="container">
		<camera device-position="front" flash="off" @error="error" style="width: 100%; height: 350px"
			class="camera-container"></camera>
		<view class="tip-container">
			<van-button form-type="submit" block @click="faceSignIn">拍照验证</van-button>
		</view>
		<van-toast id="van-toast" />
	</view>
</template>

<script>
	import Toast from "../../../wxcomponents/@vant/weapp/dist/toast/toast.js";
	export default {
		data() {
			return {
				src: "",
				classId: 0,
				userId: 0,
				sid: 0,
				confidence: 0,
				face_token: "",
				username: ""
			};
		},
		created() {
			uni.getStorage({
				key: "face_token",
				success: (res) => {
					this.face_token = res.data;
				},
			});
		},
		mounted() {
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
			uni.getStorage({
				key: "signInID",
				success: (res) => {
					this.sid = res.data;
				},
			});
			uni.getStorage({
				key: "fullName",
				success: (res) => {
					this.username = res.data;
				},
			});
		},
		onLoad: function(options) {
			console.log(options);
			this.face_token = options.face_token;
		},
		methods: {
			faceSignIn() {
				//拍照上传图片
				const ctx = uni.createCameraContext();
				ctx.takePhoto({
					quality: "high",
					success: (res) => {
						this.src = res.tempImagePath;
						Toast.loading({
							message: "识别中...",
							forbidClick: true,
						});
						//检测人脸
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
								let obj = JSON.parse(res.data);
								if (obj["faces"][0] == null || obj["faces"][0] == "") {
									//Toast获取不到人脸
									Toast.fail("获取不到人脸");
								} else {
									this.compareFace();
								}
							},
						});
					},
				});
			},
			//对比人脸
			compareFace() {
				uni.uploadFile({
					filePath: this.src,
					name: "image_file1",
					url: "https://api-cn.faceplusplus.com/facepp/v3/compare",
					header: {
						"Content-Type": "multipart/form-data",
					},
					formData: {
						api_key: "tmjzhJZqDRRCoUk1UXbH8IZPYr002EWI",
						api_secret: "p6LBqyYqjBhOysEVFE7pGpUY43v5OWdh",
						face_token2: this.face_token,
					},
					success: (res) => {
						console.log(res.data);
						let obj = JSON.parse(res.data);
						console.log(obj.confidence);
						if (obj.confidence > 80) {
							Toast.success("识别成功");
							//判断为同一个人
							this.studentSignIn(); //签到成功
						} else {
							Toast.fail("面部信息不符");
						}
					},
				});
			},
			//学生签到
			studentSignIn() {
				uni.request({
					url: "https://www.huruilinweb.top/KbdBackEnd/api/student/signIn",
					method: "POST",
					data: {
						classId: this.classId,
						sid: this.sid,
						userId: this.userId,
						username: this.username,
						finish: "1",
					},
					header: {
						"content-type": "application/x-www-form-urlencoded;charset=utf-8",
					},
					success: (res) => {
						console.log(res.data);
						setTimeout(() => {
							uni.navigateTo({
								url: "/pages/Index/success/SignInSuccess",
							});
						}, 1000);
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