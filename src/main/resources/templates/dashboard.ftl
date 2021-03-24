<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="/static/assets/images/favicon.png">
    <title>Coder-Plus</title>
    <!-- Bootstrap Core CSS -->
    <link href="/static/assets/node_modules/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="/static/css/style.css" rel="stylesheet">
    <!-- You can change the them    e colors from here -->
    <link href="/static/css/colors/default.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="fix-header card-no-border fix-sidebar">
<!-- ============================================================== -->
<!-- Preloader - style you can find in spinners.css -->
<!-- ============================================================== -->
<div class="preloader">
    <div class="loader">
        <div class="loader__figure"></div>
        <p class="loader__label">CoderPlus代码生成工具</p>
    </div>
</div>
<!-- ============================================================== -->
<!-- Main wrapper - style you can find in pages.scss -->
<!-- ============================================================== -->
<div id="main-wrapper">
    <!-- ============================================================== -->
    <!-- Topbar header - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <header class="topbar">
        <nav class="navbar top-navbar navbar-expand-md navbar-light">
            <!-- ============================================================== -->
            <!-- Logo -->
            <!-- ============================================================== -->
            <div class="navbar-header">
                <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                <!-- Dark Logo icon -->
                <img src="/static/assets/images/logo-icon.png" alt="homepage" class="dark-logo"/>
                <!--End Logo icon -->
                <b class="wi wi-sunset">&nbsp;&nbsp;&nbsp;Coder-Plus代码生成器</b>
            </div>
            <!-- ============================================================== -->
            <!-- End Logo -->
            <!-- ============================================================== -->
            <div class="navbar-collapse">
                <!-- ============================================================== -->
                <!-- toggle and nav items -->
                <!-- ============================================================== -->
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a class="nav-link nav-toggler hidden-md-up waves-effect waves-dark"
                                            href="javascript:void(0)"><i class="fa fa-bars"></i></a></li>
                    <!-- ============================================================== -->
                    <!-- Search -->
                    <!-- ============================================================== -->
                    <li class="nav-item hidden-xs-down search-box"><a
                                class="nav-link hidden-sm-down waves-effect waves-dark" href="javascript:void(0)"><i
                                    class="fa fa-search"></i></a>
                        <form class="app-search">
                            <input type="text" class="form-control" placeholder="Search & enter"> <a class="srh-btn"><i
                                        class="fa fa-times"></i></a></form>
                    </li>
                </ul>
                <!-- ============================================================== -->
                <!-- User profile and search -->
                <!-- ============================================================== -->
                <ul class="navbar-nav my-lg-0">
                    <!-- ============================================================== -->
                    <!-- Profile -->
                    <!-- ============================================================== -->
                    <li class="nav-item dropdown u-pro">
                        <a class="nav-link dropdown-toggle waves-effect waves-dark profile-pic" href=""
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img
                                    src="/static/assets/images/users/mt-logo.jpg" alt="user" class=""/> <span
                                    class="hidden-md-down">XD.Wang</span> </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- ============================================================== -->
    <!-- End Topbar header -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div class="scroll-sidebar">
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <#list nodes as menu_node>
                        <li><a class="waves-effect waves-dark" href="${menu_node.url}" aria-expanded="false"><i
                                        class="fa fa-tachometer"></i><span
                                        class="hide-menu">${menu_node.text}</span></a>
                        </li>
                    </#list>
                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>
    <!-- ============================================================== -->
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Page wrapper  -->
    <!-- ============================================================== -->
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->
        <div class="container-fluid">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="row page-titles">
                <div class="col-md-5 align-self-center">
                    <input type="hidden" value="${node.nodeType}" id="nodeType">
                    <h3 class="text-themecolor">${node.text}</h3>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <!-- Row -->
            <div class="row">
                <!-- 代码配置 -->
                <div class="col-lg-10 col-xlg-5 col-md-10">
                    <div class="card">
                        <!-- Tab panes -->
                        <div class="card-body">
                            <form class="form-horizontal form-material">
                                <div class="form-group">
                                    <label class="col-md-12">代码作者（必填）</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="XD.Wang" id="authorTxt"
                                               class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="biz-input" class="col-md-12">基础包名</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               placeholder="com.sankuai.meituan.waimai.service.dal.repository"
                                               class="form-control form-control-line" name="biz-input"
                                               id="pkgTxt">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="biz-input" class="col-md-12">自定义实体名</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               placeholder="my_model"
                                               class="form-control form-control-line" name="biz-input"
                                               id="customizedBizTxt">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="biz-input" class="col-md-12">代码文件路径</label>
                                    <div class="col-md-12">
                                        <input type="text" value="/Users/wangxiandui/Documents/gen-codes"
                                               class="form-control form-control-line" name="workspace-path"
                                               id="workspaceTxt">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">目标语言</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line">
                                            <option>Java</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">驱动语句（必填）</label>
                                    <div class="col-md-12">
                                        <textarea id="driveCodeTxt" rows="15"
                                                  class="form-control form-control-line"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button class="btn btn-success" type="button" onclick="generate()">生成代码</button>
                                        <button class="btn btn-warning" type="button" onclick="download()">下载代码</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- 代码展示 -->
                <div class="col-lg-10 col-xlg-6 col-md-10">
                    <div class="card">
                        <!-- Tab panes -->
                        <div class="card-body">
                            <form class="form-horizontal form-material">
                                <div class="form-group">
                                    <label class="col-md-12"><b>实体类</b></label>
                                    <div class="col-md-12">
                                        <textarea rows="10" id="model"
                                                  class="form-control form-control-line"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12"><b>Mapper接口类</b></label>
                                    <div class="col-md-12">
                                        <textarea rows="10" id="mapper"
                                                  class="form-control form-control-line"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12"><b>Repository接口类</b></label>
                                    <div class="col-md-12">
                                        <textarea rows="10" id="repository"
                                                  class="form-control form-control-line"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12"><b>Repository实现类</b></label>
                                    <div class="col-md-12">
                                        <textarea rows="10" id="repositoryImpl"
                                                  class="form-control form-control-line"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12"><b>MyBatis-XML</b></label>
                                    <div class="col-md-12">
                                        <textarea rows="10" id="xml" class="form-control form-control-line"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12"><b>MyBatis-Example</b></label>
                                    <div class="col-md-12">
                                        <textarea rows="10" id="example"
                                                  class="form-control form-control-line"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </footer>
        <!-- ============================================================== -->
        <!-- End footer -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->
</div>
<script>

    function generate() {
        const req = {
            "driveCode": $("#driveCodeTxt").val(),
            "nodeType": $("#nodeType").val(),
            "author": $("#authorTxt").val(),
            "pkg": $("#pkgTxt").val(),
            "customizedModelName": $("#customizedModelName").val(),
        }
        $.ajax({
            contentType: "application/json",
            url: "/dashboard/generate",
            type: "post",
            async: false,
            data: JSON.stringify(req),
            success: function (obj) {
                console.log(obj.msg)
                if (obj.suc) {
                    $("#model").text(obj.model);
                    $("#mapper").text(obj.mapper);
                    $("#repository").text(obj.repository);
                    $("#repositoryImpl").text(obj.repositoryImpl);
                    $("#xml").text(obj.xml);
                    $("#example").text(obj.example);
                } else {
                    alert(obj.msg)
                }
            }
        })
    }

    function download() {
        const req = {
            "driveCode": $("#driveCodeTxt").val(),
            "nodeType": $("#nodeType").val(),
            "author": $("#authorTxt").val(),
            "pkg": $("#pkgTxt").val(),
            "workspacePath": $("#workspaceTxt").val(),
            "customizedModelName": $("#customizedModelName").val(),
        }
        $.ajax({
            contentType: "application/json",
            url: "/dashboard/download",
            type: "post",
            async: false,
            data: JSON.stringify(req),
            success: function (obj) {
                console.log(obj.msg)
                if (!obj.suc) {
                    alert(obj.msg)
                }
            }
        })
    }

</script>
<script src="/static/assets/node_modules/jquery/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="/static/assets/node_modules/bootstrap/js/popper.min.js"></script>
<script src="/static/assets/node_modules/bootstrap/js/bootstrap.min.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="/static/js/perfect-scrollbar.jquery.min.js"></script>
<!--Wave Effects -->
<script src="/static/js/waves.js"></script>
<!--Menu sidebar -->
<script src="/static/js/sidebarmenu.js"></script>
<!--Custom JavaScript -->
<script src="/static/js/custom.min.js"></script>
</body>

</html>