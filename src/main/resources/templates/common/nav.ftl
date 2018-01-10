
<style>

    body {
        position: relative;
        overflow-x: hidden;
    }
    body,
    html {
        height: 100%;
        /*background-color: #583e7e;*/
    }
    .nav .open > a {
        background-color: transparent;
    }
    .nav .open > a:hover {
        background-color: transparent;
    }
    .nav .open > a:focus {
        background-color: transparent;
    }
    /*-------------------------------*/
    /*           Wrappers            */
    /*-------------------------------*/
    #wrapper {
        -moz-transition: all 0.5s ease;
        -o-transition: all 0.5s ease;
        -webkit-transition: all 0.5s ease;
        padding-left: 0;
        transition: all 0.5s ease;
    }
    #wrapper.toggled {
        padding-left: 180px;
    }
    #wrapper.toggled #sidebar-wrapper {
        width: 180px;
    }
    #sidebar-wrapper {
        -moz-transition: all 0.5s ease;
        -o-transition: all 0.5s ease;
        -webkit-transition: all 0.5s ease;
        background: #1a1a1a;
        height: 100%;
        left: 220px;
        margin-left: -220px;
        overflow-x: hidden;
        overflow-y: auto;
        transition: all 0.5s ease;
        width: 0;
        z-index: 1000;
    }
    #sidebar-wrapper::-webkit-scrollbar {
        display: none;
    }
    #page-content-wrapper {
        padding-top: 70px;
        width: 100%;
    }
    /*-------------------------------*/
    /*     Sidebar nav styles        */
    /*-------------------------------*/
    .sidebar-nav {
        list-style: none;
        margin: 0;
        padding: 0;
        position: absolute;
        top: 0;
        width: 220px;
    }
    .sidebar-nav li {
        display: inline-block;
        line-height: 20px;
        position: relative;
        width: 100%;
    }
    .sidebar-nav li:before {
        background-color: #1c1c1c;
        content: '';
        height: 100%;
        left: 0;
        position: absolute;
        top: 0;
        -webkit-transition: width 0.2s ease-in;
        transition: width 0.2s ease-in;
        width: 3px;
        z-index: -1;
    }
    .sidebar-nav li:first-child a {
        background-color: #1a1a1a;
        color: #ffffff;
    }
    .sidebar-nav li:nth-child(2):before {
        background-color: #402d5c;
    }
    .sidebar-nav li:nth-child(3):before {
        background-color: #4c366d;
    }
    .sidebar-nav li:nth-child(4):before {
        background-color: #583e7e;
    }
    .sidebar-nav li:nth-child(5):before {
        background-color: #64468f;
    }
    .sidebar-nav li:nth-child(6):before {
        background-color: #704fa0;
    }
    .sidebar-nav li:nth-child(7):before {
        background-color: #7c5aae;
    }
    .sidebar-nav li:nth-child(8):before {
        background-color: #8a6cb6;
    }
    .sidebar-nav li:nth-child(9):before {
        background-color: #987dbf;
    }
    .sidebar-nav li:hover:before {
        -webkit-transition: width 0.2s ease-in;
        transition: width 0.2s ease-in;
        width: 100%;
    }
    .sidebar-nav li a {
        color: #dddddd;
        display: block;
        padding: 10px 15px 10px 30px;
        text-decoration: none;
    }
    .sidebar-nav li.open:hover before {
        -webkit-transition: width 0.2s ease-in;
        transition: width 0.2s ease-in;
        width: 100%;
    }
    .sidebar-nav .dropdown-menu {
        background-color: #222222;
        border-radius: 0;
        border: none;
        box-shadow: none;
        margin: 0;
        padding: 0;
        position: relative;
        width: 100%;
    }
    .sidebar-nav li a:hover,
    .sidebar-nav li a:active,
    .sidebar-nav li a:focus,
    .sidebar-nav li.open a:hover,
    .sidebar-nav li.open a:active,
    .sidebar-nav li.open a:focus {
        background-color: transparent;
        color: #ffffff;
        text-decoration: none;
    }
    .sidebar-nav > .sidebar-brand {
        font-size: 20px;
        height: 65px;
        line-height: 44px;
    }
    /*-------------------------------*/
    /*       Hamburger-Cross         */
    /*-------------------------------*/
    .hamburger {
        background: transparent;
        border: none;
        display: block;
        height: 32px;
        margin-left: 15px;
        position: fixed;
        top: 20px;
        width: 32px;
        z-index: 999;
    }
    .hamburger:hover {
        outline: none;
    }
    .hamburger:focus {
        outline: none;
    }
    .hamburger:active {
        outline: none;
    }
    .hamburger.is-closed:before {
        -webkit-transform: translate3d(0, 0, 0);
        -webkit-transition: all 0.35s ease-in-out;
        color: #ffffff;
        content: '';
        display: block;
        font-size: 14px;
        line-height: 32px;
        opacity: 0;
        text-align: center;
        width: 100px;
    }
    .hamburger.is-closed:hover before {
        -webkit-transform: translate3d(-100px, 0, 0);
        -webkit-transition: all 0.35s ease-in-out;
        display: block;
        opacity: 1;
    }
    .hamburger.is-closed:hover .hamb-top {
        -webkit-transition: all 0.35s ease-in-out;
        top: 0;
    }
    .hamburger.is-closed:hover .hamb-bottom {
        -webkit-transition: all 0.35s ease-in-out;
        bottom: 0;
    }
    .hamburger.is-closed .hamb-top {
        -webkit-transition: all 0.35s ease-in-out;
        background-color: rgba(255, 255, 255, 0.7);
        top: 5px;
    }
    .hamburger.is-closed .hamb-middle {
        background-color: rgba(255, 255, 255, 0.7);
        margin-top: -2px;
        top: 50%;
    }
    .hamburger.is-closed .hamb-bottom {
        -webkit-transition: all 0.35s ease-in-out;
        background-color: rgba(255, 255, 255, 0.7);
        bottom: 5px;
    }
    .hamburger.is-closed .hamb-top,
    .hamburger.is-closed .hamb-middle,
    .hamburger.is-closed .hamb-bottom,
    .hamburger.is-open .hamb-top,
    .hamburger.is-open .hamb-middle,
    .hamburger.is-open .hamb-bottom {
        height: 4px;
        left: 0;
        position: absolute;
        width: 100%;
    }
    .hamburger.is-open .hamb-top {
        -webkit-transform: rotate(45deg);
        -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        background-color: #ffffff;
        margin-top: -2px;
        top: 50%;
    }
    .hamburger.is-open .hamb-middle {
        background-color: #ffffff;
        display: none;
    }
    .hamburger.is-open .hamb-bottom {
        -webkit-transform: rotate(-45deg);
        -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        background-color: #ffffff;
        margin-top: -2px;
        top: 50%;
    }
    .hamburger.is-open:before {
        -webkit-transform: translate3d(0, 0, 0);
        -webkit-transition: all 0.35s ease-in-out;
        color: #ffffff;
        content: '';
        display: block;
        font-size: 14px;
        line-height: 32px;
        opacity: 0;
        text-align: center;
        width: 100px;
    }
    .hamburger.is-open:hover before {
        -webkit-transform: translate3d(-100px, 0, 0);
        -webkit-transition: all 0.35s ease-in-out;
        display: block;
        opacity: 1;
    }


</style>

<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <li>
            <a href="/sell/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/product/list">列表</a></li>
                <li><a href="/sell/seller/product/index">新增</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/category/list">列表</a></li>
                <li><a href="/sell/seller/category/index">新增</a></li>
            </ul>
        </li>

        <li>
            <a href="/sell/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>