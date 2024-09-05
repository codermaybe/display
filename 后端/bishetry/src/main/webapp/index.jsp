<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="HtmlTool.UserInfo" %>
<%@ page import="javafx.scene.control.Alert" %>
<%@ page import="HtmlTool.UserList" %>
<%@ page import="SocketTool.BroadcastListener" %>
<%@ page import="SocketTool.UdpBroadcast" %>
<%@ page import="com.sun.org.apache.bcel.internal.generic.LoadClass" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="HtmlTool.StringCodeSolver" %>
<%@ page import="MsgProcessor.MsgReciver" %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>		
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">
	<title>开始聊天吧</title>
	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="assets/fonts/material-icon/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" href="assets/vendor/bootstrap-datepicker/css/bootstrap-datepicker.min.css">
	
	
	<link rel="stylesheet" href="assets/css/style.min.css">

	</head>


	<body>
		<div id="layout" class="theme-cyan">
			<div class="navigation navbar justify-content-center py-xl-4 py-md-3 py-0 px-3">
				<a href="index.jsp" title="Postman" class="brand">
					<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 46 46" fill="none">
						<g id="logo-icon-color">
							<path id="Vector" d="M26.4966 6.01307V2.00436L22.9746 0L19.5029 2.00436V6.01307L22.9746 8.01743L26.4966 6.01307Z" fill="#4539CF" />
							<path id="Vector_2" d="M34.7989 10.8235V6.81477L31.3272 4.81042L27.8555 6.81477V10.8235L31.3272 12.8279L34.7989 10.8235Z" fill="#4539CF" />
							<path id="Vector_3" d="M43 15.4837V11.4749L39.5283 9.47058L36.0063 11.4749V15.4837L39.5283 17.488L43 15.4837Z" fill="#4539CF" />
							<path id="Vector_ 4" d="M43 25.0043V20.9956L39.5283 18.9913L36.0063 20.9956V25.0043L39.5283 27.0087L43 25.0043Z" fill="#4539CF" />
							<path id="Vector_5" d="M33.9935 19.9935V16.9368L31.3269 15.4336L28.6602 16.9368V19.9935L31.3269 21.5469L33.9935 19.9935Z" fill="#4539CF" />
							<path id="Vector_6" d="M33.9935 29.5142V26.4575L31.3269 24.9041L28.6602 26.4575V29.5142L31.3269 31.0174L33.9935 29.5142Z" fill="#4539CF" />
							<path id="Vector_7" d="M15.931 19.6928V17.2876L13.8178 16.085L11.7549 17.2876V19.6928L13.8178 20.8453L15.931 19.6928Z" fill="#4539CF" />
							<path id="Vector_8" d="M15.931 29.1634V26.7582L13.8178 25.5555L11.7549 26.7582V29.1634L13.8178 30.366L15.931 29.1634Z" fill="#4539CF" />
							<path id="Vector_9" d="M6.4717 24.0022V21.9978L4.76101 20.9956L3 21.9978V24.0022L4.76101 25.0044L6.4717 24.0022Z" fill="#4539CF" />
							<path id="Vector_10" d="M43 34.4749V30.4662L39.5283 28.4619L36.0063 30.4662V34.4749L39.5283 36.4793L43 34.4749Z" fill="#4539CF" />
							<path id="Vector_11" d="M25.9433 15.1329V11.8758L23.1257 10.2222L20.2578 11.8758V15.1329L23.1257 16.7865L25.9433 15.1329Z" fill="#4539CF" />
							<path id="Vector_12" d="M25.9433 34.1242V30.8671L23.1257 29.2135L20.2578 30.8671V34.1242L23.1257 35.7778L25.9433 34.1242Z" fill="#4539CF" />
							<path id="Vector_13" d="M25.4908 24.3529V21.597L23.126 20.244L20.7109 21.597V24.3529L23.126 25.756L25.4908 24.3529Z" fill="#4539CF" />
							<path id="Vector_14" d="M34.7989 39.1852V35.1765L31.3272 33.1721L27.8555 35.1765V39.1852L31.3272 41.1896L34.7989 39.1852Z" fill="#4539CF" />
							<path id="Vector_15" d="M16.6856 10.2222V6.91507L13.8176 5.26147L10.9497 6.91507V10.2222L13.8176 11.8257L16.6856 10.2222Z" fill="#4539CF" />
							<path id="Vector_16" d="M6.22013 12.9782V11.1242L4.61006 10.1721L3 11.1242V12.9782L4.61006 13.9303L6.22013 12.9782Z" fill="#4539CF" />
							<path id="Vector_17" d="M26.4966 43.9956V39.9868L22.9746 37.9825L19.5029 39.9868V43.9956L22.9746 45.9999L26.4966 43.9956Z" fill="#4539CF" />
							<path id="Vector_18" d="M16.6856 39.0849V35.7777L13.8176 34.1241L10.9497 35.7777V39.0849L13.8176 40.7385L16.6856 39.0849Z" fill="#4539CF" />
							<path id="Vector_19" d="M7.5283 34.8257V32.22L5.26415 30.9172L3 32.22V34.8257L5.26415 36.1285L7.5283 34.8257Z" fill="#4539CF" />
						</g>
					</svg>
				</a>
				<div class="nav flex-md-column nav-pills flex-grow-1" role="tablist" aria-orientation="vertical">
					<a class="mb-xl-3 mb-md-2 nav-link active" data-toggle="pill" href="#nav-tab-chat" role="tab"><i class="zmdi zmdi-comment-alt"></i></a>
					
					
				</div>
				<div class="nav flex-md-column nav-pills flex-grow-2" role="tablist" aria-orientation="vertical">
					<a class="mt-xl-3 mt-md-2 nav-link light-dark-toggle" href=""><i class="zmdi zmdi-brightness-2"></i><input class="light-dark-btn" type="checkbox" id="styleSwitch"  onclick="changestyle()"></a>
				</div>

			</div>
			<div class="sidebar border-end py-xl-4 py-3 px-xl-4 px-3">
				<div class="tab-content">


					<div class="tab-pane fade show active" id="nav-tab-chat" role="tabpanel">

						<div class="d-flex justify-content-between align-items-center mb-4">
							<h3 class="mb-0 text-primary">P2P局域网聊天工具</h3>
						</div>

						<div class="form-group input-group-lg search mb-3">
							<i class="zmdi zmdi-search"></i>
							<input type="text" class="form-control" placeholder="Search...">
						</div>

						<ul class="chat-list" id="ChatList">

							<%!//全局变量，本地用户
								UserInfo localuser;
                                UdpBroadcast broadcaster;
                                BroadcastListener listener;

							%>
							<%

								//初始化本地用户的信息
                                if(application.getAttribute("localuser")==null){
                                	request.setCharacterEncoding("utf-8");
									localuser = new UserInfo(StringCodeSolver.Encode(request.getParameter("username")) ,StringCodeSolver.Encode(request.getParameter("UserBackupInfo")),request.getParameter("ismale").equals("true"));
									//保存本地用户(暂时不用)
									application.setAttribute("localuser",localuser);
									application.setAttribute("localuserKey",localuser.getName()+localuser.getId());
                                }


								//保存远程对话用户(初值为空)
								application.setAttribute("RemoteUserName","");
								application.setAttribute("RemoteUserId","");
								application.setAttribute("RemoteUser",localuser);
								//查看是否建立了监听器
								if(application.getAttribute("ListenerSetted")==null){
									broadcaster = new UdpBroadcast(localuser);
									listener = new BroadcastListener();
									broadcaster.start();
									listener.start();
									//同时顺便启动消息接收
									String cachePath =(String)application.getRealPath(File.separator+ "CaChe");
									application.setAttribute("ListenerSetted",true);
									application.setAttribute("cachePath",cachePath);
									new Thread(new MsgReciver(cachePath),"监听聊天信息").start();
								}

                                //用户列表，用哈希图存储在监听器中

								Timer time = new Timer();
								time.schedule(new TimerTask() {
									@Override
									public void run() {
										try {
											//获取除去自己的用户map
											application.setAttribute("userlist",listener.getHashMapFromListener());
										} catch (UnsupportedEncodingException e) {
											e.printStackTrace();
										}
									}
								},0,1000);

							%>

						</ul>
					</div>

				</div>
			</div>


			<!-- 右边侧栏，不动-->
			<div class="rightbar d-none d-md-block">

				<div class="nav flex-column nav-pills border-start py-xl-4 py-3 h-100">
					
					<a class="nav-link mb-2 text-center" href="#"><i class="zmdi zmdi-plus"></i></a>
				</div>

			</div>
            <!-- 加载页 -->
			<div id="InitPage"  class="main px-xl-5 px-lg-4 px-3">

				<div class="chat-body">

					<div class="chat d-flex justify-content-center align-items-center h-100 text-center py-xl-4 py-md-3 py-2">
						<div class="container-xxl">
							<div class="avatar lg avatar-bg me-auto ms-auto mb-5">
								<img class="avatar lg rounded-circle border" src="assets/images/user.png" alt="" />
								<span class="a-bg-1"></span>
								<span class="a-bg-2"></span>
							</div>
							<h5 class="font-weight-bold">Hey, <%=StringCodeSolver.Decode(localuser.getName())%></h5>
							<p>选择朋友开始聊天吧!</p>
						</div>
					</div>

				</div>

			</div>
			<!-- 点击用户框后的聊天页面-->
			<div id="UserChatPage" style="display: none;" class="main px-xl-5 px-lg-4 px-3" >

				<div class="chat-body">

					<div class="chat-header border-bottom py-xl-4 py-md-3 py-2">
						<div class="container-xxl">
							<div class="row align-items-center">

								<div class="col-6 col-xl-4">
									<div class="media">
										<div class="me-3 show-user-detail">
											<span class="status rounded-circle"></span>
											<img class="avatar rounded-circle" src="assets/images/xs/avatar5.jpg"  alt="avatar" id="ChatIcon">
										</div>
										<div class="media-body overflow-hidden">
											<div class="d-flex align-items-center mb-1">
												<h6 class="text-truncate mb-0 me-auto" id="Chatname">Jason Porter</h6>
											</div>
											<div class="text-truncate" id ="ChatInfo"  >online</div>
										</div>
									</div>
								</div>


							</div>
						</div>
					</div>


					<div class="chat-content">
						<div class="container-xxl">
							<ul class="list-unstyled py-4" id="ChatRoom">

							</ul>
						</div>
					</div>

					<div class="chat-footer border-top py-xl-4 py-lg-2 py-2">
						<div class="container-xxl">
							<div class="row">
								<div class="col-12">
									<div class="input-group align-items-center">

										<form target="frameName" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;" action="<%=request.getContextPath()%>/UploadServlet" method="post" enctype="multipart/form-data">

											<input type="text" id="textMsgHidden" name="textMsgHidden" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;" >
											<!--图片输入-->
											<input type="file" id="ImgMsgHidden" name="ImgMsgHidden"  accept="image/*" multiple="multiple" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/>
											<!--文件输入-->
											<input type="file" id="FileMsgHidden" name="FileMsgHidden" multiple="multiple" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/>

											<input type="submit" id="UploadButton" />
										</form>
										<!--结合form的target属性阻止表单跳转-->
										<iframe src="" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;" frameborder="0" name="frameName"></iframe>



										<input type="text" class="form-control border-0 pl-0" placeholder="Type your message..." id="textMSg">


										<div class="input-group-append d-none d-sm-block"><span class="input-group-text border-0"><button class="btn btn-sm btn-link text-muted"  title="Refresh" type="button" id="Refresh"><i class="zmdi zmdi-refresh font-22"></i></button></span></div>





										<div class="input-group-append"><span class="input-group-text border-0"><button class="btn btn-sm btn-link text-muted"  title="Smaily" type="button" id="ImgUpdate" ><i class="zmdi zmdi-mood font-22"></i></button></span></div>
										<!--设置隐藏的input框，在点击选择button时间接触发（文件）-->

										<div class="input-group-append" >
											<span class="input-group-text border-0" ><button  class="btn btn-sm btn-link text-muted"  title="Attachment" type="button" id="FileUpdate"><i class="zmdi zmdi-attachment font-22"></i></button></span>
										</div>


										<div class="input-group-append"><span class="input-group-text border-0 pr-0"><button type="submit" class="btn btn-primary" id="MsgSend"><span class="d-none d-md-inline-block me-2">Send</span><i class="zmdi zmdi-mail-send"></i></button>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>

		<script src="assets/vendor/jquery/jquery-3.6.0.js" type="text/javascript"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
		<script src="assets/js/template.js" type="text/javascript"></script>
		<script src="https://ajax.cloudflare.com/cdn-cgi/scripts/7089c43e/cloudflare-static/rocket-loader.min.js" data-cf-settings="67ae01ba4dd2a59f70ab9427-|49" defer="" ></script>
		<script defer src="https://static.cloudflareinsights.com/beacon.min.js" data-cf-beacon='{"r":1,"version":"2021.2.0","rayId":"6203529a7c4f3684","si":10}'></script>
        <script src="assets/js/chatCard.js" type="text/javascript"></script>
		<script src="assets/js/ChatRoomAjax.js"></script>
	    <script src="assets/js/MessageDealer.js"></script>

	</body>

</html>