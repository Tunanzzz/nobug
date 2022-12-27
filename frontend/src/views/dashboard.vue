<template>
  <div class="outer-box">
        <!-- 最顶层 -->
        <div class="outer-top-box">
            <!-- 左侧两个彩色方块 -->
            <div class="top-left">
                <!-- 方块一 -->
                <div class="left-box">
                    <div class="left-content">
                        <div class="content-top">
                            <div class="top-text">创建事项</div>
                            <n-icon size="40" color="#1f78ff" class="top-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><g fill="none"><path d="M16 30c7.732 0 14-6.268 14-14S23.732 2 16 2S2 8.268 2 16s6.268 14 14 14zm-1-19a1 1 0 1 1 2 0v4h4a1 1 0 1 1 0 2h-4v4a1 1 0 1 1-2 0v-4h-4a1 1 0 1 1 0-2h4v-4z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                        <div class="content-bottom">快速创建事项</div>
                    </div>
                </div>
                <!-- 方块二 -->
                <div class="left-box">
                    <div class="left-content" id="bottom-box">
                        <div class="content-top">
                            <div class="top-text">去规划</div>
                            <n-icon size="40" color="#6cdaff" class="top-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><g fill="none"><path d="M2 16C2 8.268 8.268 2 16 2s14 6.268 14 14s-6.268 14-14 14S2 23.732 2 16zm13.293-6.707a1 1 0 0 0 0 1.414L19.586 15H10a1 1 0 1 0 0 2h9.586l-4.293 4.293a1 1 0 0 0 1.414 1.414l6-6a1 1 0 0 0 0-1.414l-6-6a1 1 0 0 0-1.414 0z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                        <div class="content-bottom">前往规划迭代</div>
                    </div>
                </div>
            </div>
            <!-- 右侧两盒子 -->
            <div class="top-right">
                <div class="right-box">
                    <div class="right-content">
                        <div class="content-left">
                            <n-icon size="20" class="left-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 6h16"></path><path d="M10 12h10"></path><path d="M6 18h14"></path></g></svg>
                            </n-icon>
                            <div class="left-text">我未完成的</div>
                        </div>
                        <div class="content-right">
                            <n-icon size="15" color="#d2d5d9" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M4.47 2.22a.75.75 0 0 0 0 1.06L7.19 6L4.47 8.72a.75.75 0 0 0 1.06 1.06l3.25-3.25a.75.75 0 0 0 0-1.06L5.53 2.22a.75.75 0 0 0-1.06 0z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                    </div>
                    <div class="right-content">
                        <div class="content-left">
                            <n-icon size="20" class="left-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 6h16"></path><path d="M10 12h10"></path><path d="M6 18h14"></path></g></svg>
                            </n-icon>
                            <div class="left-text">分配给我的</div>
                        </div>
                        <div class="content-right">
                            <n-icon size="15" color="#d2d5d9" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M4.47 2.22a.75.75 0 0 0 0 1.06L7.19 6L4.47 8.72a.75.75 0 0 0 1.06 1.06l3.25-3.25a.75.75 0 0 0 0-1.06L5.53 2.22a.75.75 0 0 0-1.06 0z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                    </div>
                    <div class="right-content">
                        <div class="content-left">
                            <n-icon size="20" class="left-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 6h16"></path><path d="M10 12h10"></path><path d="M6 18h14"></path></g></svg>
                            </n-icon>
                            <div class="left-text">我创建的</div>
                        </div>
                        <div class="content-right">
                            <n-icon size="15" color="#d2d5d9" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M4.47 2.22a.75.75 0 0 0 0 1.06L7.19 6L4.47 8.72a.75.75 0 0 0 1.06 1.06l3.25-3.25a.75.75 0 0 0 0-1.06L5.53 2.22a.75.75 0 0 0-1.06 0z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                    </div>
                    <div class="right-content">
                        <div class="content-left">
                            <n-icon size="20" class="left-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 6h16"></path><path d="M10 12h10"></path><path d="M6 18h14"></path></g></svg>
                            </n-icon>
                            <div class="left-text">我关注的</div>
                        </div>
                        <div class="content-right">
                            <n-icon size="15" color="#d2d5d9" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M4.47 2.22a.75.75 0 0 0 0 1.06L7.19 6L4.47 8.72a.75.75 0 0 0 1.06 1.06l3.25-3.25a.75.75 0 0 0 0-1.06L5.53 2.22a.75.75 0 0 0-1.06 0z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                    </div>
                    <div class="right-content">
                        <div class="content-left">
                            <n-icon size="20" class="left-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 6h16"></path><path d="M10 12h10"></path><path d="M6 18h14"></path></g></svg>
                            </n-icon>
                            <div class="left-text">全部打开的</div>
                        </div>
                        <div class="content-right">
                            <n-icon size="15" color="#d2d5d9" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M4.47 2.22a.75.75 0 0 0 0 1.06L7.19 6L4.47 8.72a.75.75 0 0 0 1.06 1.06l3.25-3.25a.75.75 0 0 0 0-1.06L5.53 2.22a.75.75 0 0 0-1.06 0z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                    </div>
                </div>
                <div class="left-box">
                    <div class="left-content">
                        <img src="https://img.codesocean.top/image/1657690934348" class="content-img">
                        <div class="content-text">1任务进行中</div>
                        <div class="content-line"></div>
                        <div class="content-iteration">
                            <div class="iteration-text">全部任务</div>
                            <n-icon size="15" color="#0066ff" class="iteration-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M1.5 6a.75.75 0 0 1 .75-.75h5.94L6.22 3.28a.75.75 0 0 1 1.06-1.06l3.25 3.25a.75.75 0 0 1 0 1.06L7.28 9.78a.75.75 0 0 1-1.06-1.06l1.97-1.97H2.25A.75.75 0 0 1 1.5 6z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                    </div>
                    <div class="right-content">
                        <div class="content-left">
                            <n-icon size="20" class="left-img" color="#0070ff">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><path d="M18.6 19.5H21v2h-6v-6h2v2.73c1.83-1.47 3-3.71 3-6.23c0-4.07-3.06-7.44-7-7.93V2.05c5.05.5 9 4.76 9 9.95c0 2.99-1.32 5.67-3.4 7.5zM4 12c0-2.52 1.17-4.77 3-6.23V8.5h2v-6H3v2h2.4A9.966 9.966 0 0 0 2 12c0 5.19 3.95 9.45 9 9.95v-2.02c-3.94-.49-7-3.86-7-7.93zm12.24-3.89l-5.66 5.66l-2.83-2.83l-1.41 1.41l4.24 4.24l7.07-7.07l-1.41-1.41z" fill="currentColor"></path></svg>
                            </n-icon>
                            <div class="left-text">1.4.x迭代</div>
                        </div>
                        <div class="content-right">
                            <n-icon size="15" color="#d2d5d9" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M4.47 2.22a.75.75 0 0 0 0 1.06L7.19 6L4.47 8.72a.75.75 0 0 0 1.06 1.06l3.25-3.25a.75.75 0 0 0 0-1.06L5.53 2.22a.75.75 0 0 0-1.06 0z" fill="currentColor"></path></g></svg>
                            </n-icon>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 中间一层 -->
        <div class="outer-middle-box">
            <!-- 盒子一 -->
            <div class="box-left" >
                <!-- 上方标题 -->
                <div class="left-text">代码仓库</div>
                <!-- 下方盒子 -->
                <div class="left-bottom">
                    <div class="bottom-item">
                        <div class="item-text">examine-list</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">0</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">0</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">project-overlook</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">1</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">2</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">examine-scheme</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">0</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">1</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">user-manage</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">2</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">1</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">project-examine-detail</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">1</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">3</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">project-remark</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">3</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">2</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                </div>
               
            </div>
            <!-- 盒子二 -->
            <div class="box-right">
                <!-- 上方标题 -->
                <div class="right-text">构建计划</div>
                <!-- 下方盒子 -->
                <div class="right-bottom">
                    <div class="bottom-item">
                        <div class="item-text">java code-scan-pass</div>
                        <div class="item-middle">
                            <!-- <n-icon size="18" color="#8592a6" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z" fill="currentColor"></path></svg>
                            </n-icon> -->
                            <img src="https://img.codesocean.top/image/1657697568317" class="middle-img">
                            <div class="middle-text">构建成功</div>
                            <div class="middle-time">7小时前 耗时15秒</div>
                        </div>
                        <div class="item-bottom">
                            <div class="bottom-circle"></div>
                            <div class="bottom-text">定时任务自动触发</div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">vue code-scan-pass</div>
                        <div class="item-middle">
                            <!-- <n-icon size="18" color="#8592a6" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z" fill="currentColor"></path></svg>
                            </n-icon> -->
                            <img src="https://img.codesocean.top/image/1657697568317" class="middle-img">
                            <div class="middle-text">构建成功</div>
                            <div class="middle-time">14小时前 耗时50秒</div>
                        </div>
                        <div class="item-bottom">
                            <div class="bottom-circle"></div>
                            <div class="bottom-text">定时任务自动触发</div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">springboot code-scan-pass</div>
                        <div class="item-middle">
                            <!-- <n-icon size="18" color="#8592a6" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z" fill="currentColor"></path></svg>
                            </n-icon> -->
                            <img src="https://img.codesocean.top/image/1657697568317" class="middle-img">
                            <div class="middle-text">构建成功</div>
                            <div class="middle-time">19小时前 耗时43秒</div>
                        </div>
                        <div class="item-bottom">
                            <div class="bottom-circle"></div>
                            <div class="bottom-text">定时任务自动触发</div>
                        </div>
                    </div>
                </div>
               
            </div>
        </div>
        <!-- 中间一层 -->
        <div class="outer-middle-box" id="for-bottom">
            <!-- 盒子二 -->
            <div class="box-right">
                <!-- 上方标题 -->
                <div class="right-text">任务列表</div>
                <!-- 下方盒子 -->
                <div class="right-bottom">
                    <div class="bottom-item">
                        <div class="item-text">实现顶部标签拖拽功能</div>
                        <div class="item-middle">
                            <!-- <n-icon size="18" color="#8592a6" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z" fill="currentColor"></path></svg>
                            </n-icon> -->
                            <img src="https://img.codesocean.top/image/1657697568317" class="middle-img">
                            <div class="middle-text">任务完成</div>
                            <div class="middle-time">3小时前 耗时17秒</div>
                        </div>
                        <div class="item-bottom">
                            <div class="bottom-circle"></div>
                            <div class="bottom-text">用户米糊提交</div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">修改完成方案提交接口</div>
                        <div class="item-middle">
                            <!-- <n-icon size="18" color="#8592a6" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z" fill="currentColor"></path></svg>
                            </n-icon> -->
                            <img src="https://img.codesocean.top/image/1657697568317" class="middle-img">
                            <div class="middle-text">任务完成</div>
                            <div class="middle-time">22小时前 耗时1分37秒</div>
                        </div>
                        <div class="item-bottom">
                            <div class="bottom-circle"></div>
                            <div class="bottom-text">用户jinhai提交</div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">项目详情页面大修改</div>
                        <div class="item-middle">
                            <!-- <n-icon size="18" color="#8592a6" class="right-img">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z" fill="currentColor"></path></svg>
                            </n-icon> -->
                            <img src="https://img.codesocean.top/image/1657697568317" class="middle-img">
                            <div class="middle-text">任务完成</div>
                            <div class="middle-time">23小时前 耗时23秒</div>
                        </div>
                        <div class="item-bottom">
                            <div class="bottom-circle"></div>
                            <div class="bottom-text">用户米糊提交</div>
                        </div>
                    </div>
                </div>
               
            </div>
            <!-- 盒子一 -->
            <div class="box-left">
                <!-- 上方标题 -->
                <div class="left-text">缺陷修改提交</div>
                <!-- 下方盒子 -->
                <div class="left-bottom">
                    <div class="bottom-item">
                        <div class="item-text">解决历史记录页业务逻辑出错的问题。</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">1</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">0</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">解决好友页面偶现崩溃的问题。</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">5</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">2</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">解决虚拟机检测丢失数据的问题。</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">2</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">1</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">解决推荐系统工作不稳定的问题。</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">3</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">0</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">解决相关信息工作不稳定的问题。</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">0</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">2</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-item">
                        <div class="item-text">解决代码页工作不稳定的问题。</div>
                        <div class="item-bottom">
                            <!-- 左侧部分 -->
                            <div class="bottom-left">
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"><circle cx="160" cy="96" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><circle cx="160" cy="416" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M160 368V144"></path><circle cx="352" cy="160" r="48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></circle><path d="M352 208c0 128-192 48-192 160" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"></path></svg>
                                    </n-icon>
                                    <div class="left-text">1</div>
                                </div>
                                <div class="left-box">
                                    <n-icon size="15" color="#8592a6" class="left-img">
                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 20 20"><g fill="none"><path d="M11.146 6.854a.5.5 0 0 0 .708-.708L10.707 5H12.5A2.5 2.5 0 0 1 15 7.5V12a3 3 0 1 0 1 .17V7.5A3.5 3.5 0 0 0 12.5 4h-1.793l1.147-1.146a.5.5 0 0 0-.708-.708l-2 2a.5.5 0 0 0 0 .708l2 2zM17 15a2 2 0 1 1-4 0a2 2 0 0 1 4 0zM8 5a3 3 0 0 1-3 3v4.5A2.5 2.5 0 0 0 7.5 15h1.793l-1.147-1.146a.5.5 0 0 1 .708-.708l2 2a.5.5 0 0 1 0 .708l-2 2a.5.5 0 0 1-.708-.708L9.293 16H7.5A3.5 3.5 0 0 1 4 12.5V7.83A3.001 3.001 0 1 1 8 5zM7 5a2 2 0 1 0-4 0a2 2 0 0 0 4 0z" fill="currentColor"></path></g></svg>
                                    </n-icon>
                                    <div class="left-text">1</div>
                                </div>
                            </div>
                            <!-- 右侧部分 -->
                            <div class="bottom-right">
                                <n-icon size="18" color="#8592a6" class="right-img">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16"><g fill="none"><path d="M4 2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h1V9H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1v1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H4zm7 4v1h1a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1V6a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-1z" fill="currentColor"></path></g></svg>
                                </n-icon>
                            </div>
                        </div>
                    </div>
                </div>
               
            </div>
            
        </div>
  </div>
</template>

<script>
import 
{ 
    AddCircle32Filled
} 
from "@vicons/ionicons5";
export default {

}
</script>

<style lang="scss">
.outer-box{
    width: 100%;
    .outer-top-box{
        width: 100%;
        height: 250px;
        // background-color: rgb(252, 179, 179);
        display: flex;
        justify-content: space-around;
        .top-left{
            width: 11%;
            height: 100%;
            // background-color: rgb(235, 255, 183);
            .left-box{
                display: flex;
                height: 50%;
                width: 100%;
                align-items: center;
                justify-content: center;
                margin-left: 10px;
                .left-content{
                    height: 90%;
                    width: 90%;
                    margin-top: 5%;
                    background-color: hsl(210, 100%, 93%);
                    border: 1px solid #dadfe6;
                    // margin: 10px 10px;
                    border-radius: 5%;
                    display: flex;
                    justify-content: center;
                    flex-wrap: wrap;
                    .content-top{
                        margin-top: 10px;
                        display: flex;
                        width: 80%;
                        justify-content: space-between;
                        .top-text{
                            // text-align: center;
                            
                            height: 40px;
                            line-height: 40px;
                            font-size: 15px;
                        }
                        .top-img{
                            width: 40px;
                            height: 40px;
                            // background-color: #fff;
                        }
                    }
                    .content-bottom{
                        width: 80%;
                        height: 30px;
                        line-height: 30px;
                        font-size: 14px;
                        color: #7d8ca0;
                    }
                }
                #bottom-box{
                    background-color: #ccf4ff;
                }
            }
            
        }
        .top-right{
            width: 88%;
            // background-color: rgb(224, 247, 159);
            display: flex;
            justify-content: space-around;
            align-items: center;
            margin-right: 10px;

            .right-box{
                margin-top:10px;
                height: 95%;
                width: 42%;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                // background-color: rgb(186, 249, 246);
                border: 1px solid #dadfe6;
                border-radius: 10px;
                .right-content{
                    display: flex;
                    width: 90%;
                    margin-top: 7px;
                    height: 33px;
                    justify-content: space-between;
                    .content-left{
                        display: flex;
                        align-items: center;
                        .left-img{

                        }
                        .left-text{
                            margin-left: 4px;
                            line-height: 30px;
                            font-size: 15px;
                            height: 30px;
                        }
                    }
                    .content-right{
                        display: flex;
                        align-items: center;
                        .right-img{

                        }
                    }
                }
            }
            .left-box{
                width: 53%;
                margin-top:10px;
                height: 95%;
                // background-color: #fff;
                border: 1px solid #dadfe6;
                border-radius: 10px;
                display: flex;
                flex-wrap: wrap;
                overflow: hidden;
                margin-right: 30px;
                .left-content{
                    height: 100%;
                    width: 20%;
                    background-color: #ddebff;
                    .content-img{
                        width: 60px;
                        height: 50px;
                        margin-top: 10px;
                        margin-left: 10px;
                    }
                    .content-text{
                        font-size: 15px;
                        margin-left: 15px;
                        margin-top: 10px;
                        width: 60px;
                    }
                    .content-line{
                        width: 13%;
                        border: 1px solid #d5e0eb;
                        margin-left: 15px;
                        margin-top: 20px;
                    }
                    .content-iteration{
                        display: flex;
                        align-items: center;
                        height: 30px;
                        margin-left: 15px;
                        margin-top: 20px;

                        .iteration-text{
                            height: 30px;
                            line-height: 30px;
                            color: #0066ff;
                        }
                        .iteration-img{

                        }
                        
                    }
                }
                .right-content{
                    display: flex;
                    width: 72%;
                    margin: 2% 4%;
                    margin-top: 13px;
                    height: 33px;
                    justify-content: space-between;
                    .content-left{
                        display: flex;
                        align-items: center;
                        .left-img{
                            background-color: #ddebff;
                            border-radius: 2px;
                        }
                        .left-text{
                            margin-left: 6px;
                            line-height: 30px;
                            font-size: 15px;
                            height: 30px;
                        }
                    }
                    .content-right{
                        display: flex;
                        align-items: center;
                        .right-img{
                            // background-color: rgb(253, 177, 177);
                        }
                    }
                }
            
            }
        }
    }
    .outer-middle-box{
        width: 97%;
        margin-top: 20px;
        height: 320px;
        display: flex;
        justify-content: space-around;
        // background-color: rgb(252, 196, 196);
        margin-left: 1%;
        .box-left{
            width: 48.7%;
            height: 100%;
            background-color: #f4f5f7;
            border-radius: 10px;
            .left-text{
                font-size: 18px;
                height: 45px;
                line-height: 45px;
                margin-left: 10px;
            }
            .left-bottom{
                width: 96%;
                margin: 0 2%;
                display: flex;
                flex-wrap: wrap;
                .bottom-item{
                    width: 48%;
                    height: 80px;
                    background-color: #fff;
                    border-radius: 4px;
                    margin-left: 6px;
                    margin-top: 7px;
                    .item-text{
                        height: 40px;
                        line-height: 40px;
                        margin-left: 15px;
                        font-weight: bold;
                    }
                    .item-bottom{
                        display: flex;
                        justify-content: space-between;
                        .bottom-left{
                            display: flex;
                            .left-box{
                                width: 30px;
                                height: 30px;
                                display: flex;
                                align-items: center;
                                justify-content: left;
                                margin-top: 5px;
                                margin-left: 13px;
                               .left-img{
                                    

                                }
                                .left-text{
                                    margin-left: 4px;
                                    height: 30px;
                                    font-size: 14px;
                                    color: #8592a6;
                                    line-height: 30px;
                                } 
                            }
                            
                        }
                        .bottom-right{
                            .right-img{
                                margin-top: 10px;
                                margin-right: 10px;
                            }
                        }
                    }
                }
            }
        }
        .box-right{
            width: 48.7%;
            height: 100%;
            background-color: #f4f5f7;
            border-radius: 10px;
            .right-text{
                font-size: 18px;
                height: 45px;
                line-height: 45px;
                margin-left: 10px;
            }
            .right-bottom{
                width: 96%;
                margin: 0 2%;
                display: flex;
                flex-wrap: wrap;
                .bottom-item{
                    width: 100%;
                    height: 80px;
                    margin-top: 7px;
                    background-color: #fff;
                    .item-text{
                        height: 40px;
                        line-height: 40px;
                        margin-left: 15px;
                        font-weight: bold;
                    }
                    .item-middle{
                        display: flex;
                        align-items: center;
                        margin-top: -8px;
                        .middle-img{
                            width: 15px;
                            height: 15px;
                            margin-left: 13px;
                        }
                        .middle-text{
                            height: 20px;
                            line-height:20px;
                            margin-left: 7px;
                            color :#3ac27d;
                        }   
                        .middle-time{
                            margin-left: 7px;
                        }
                    }
                    .item-bottom{
                        display: flex;
                        align-items: center;
                        .bottom-circle{
                            width: 2px;
                            height: 2px;
                            border-radius: 50%;
                            margin-left: 18px;
                            border: 1px solid #b4b8be;
                        }
                        .bottom-text{
                            margin-left: 13px;
                        }
                    }
                }
            }
        }
    }
}
#for-bottom{
    margin-bottom: 20px;
}
</style>