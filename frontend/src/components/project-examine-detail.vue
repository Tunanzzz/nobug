<template>
    <div class="extraOuter">
        <div class="outer" v-if="!projectSpinning">
            <div class="topBtns">
                <div class="left">
                    <n-popover
                        trigger="hover"
                        :show-arrow="false"
                        placement="bottom"
                    >
                        <template #trigger>
                            <n-button
                                text
                                type="primary"
                                size="large"
                                @click="emit('changeScheme')"
                            >
                                <template #icon>
                                    <n-icon>
                                        <ReaderOutline />
                                    </n-icon>
                                </template>
                                <p>考核方案</p>
                            </n-button>
                        </template>
                        <span
                            >当前方案：{{ project.schemeInfo.schemeName
                            }}{{
                                project.schemeInfo.isDisable ? "(已弃用)" : ""
                            }}
                            {{
                                project.projectBaseInfo.status < 4
                                    ? "，点击可更换"
                                    : ""
                            }}</span
                        >
                    </n-popover>
                </div>
                <div class="right">
                    <n-button
                        text
                        type="primary"
                        size="large"
                        @click="
                            tabManager.selfActive(panelKey).toggleFullScreen()
                        "
                    >
                        <template #icon>
                            <n-icon>
                                <expand-outline />
                            </n-icon>
                        </template>
                        <p>演示模式</p>
                    </n-button>
                </div>
            </div>
            <div class="totalView">
                <div class="totalScore outerBox">
                    <div class="score-box-top">
                        <div class="total-score-box">
                            <div class="score-left-top">考核总分</div>
                            <div class="score-left-bottom">
                                {{ project.projectBaseInfo.score.toFixed(2) }}
                            </div>
                        </div>
                        <img
                            class="collection-right"
                            src="https://img.codesocean.top/image/1656658619801"
                        />
                    </div>
                    <div class="score-box-bottom">
                        <div class="chart-box">
                            <div
                                :id="'totalScoreChart' + projectId"
                                class="mychart"
                                style="
                                    width: 100%;
                                    height: 240px;
                                    transform: translate(-5%, 5%);
                                "
                            ></div>
                        </div>
                    </div>
                </div>
                <div class="scoreRank outerBox">
                    <div class="score-box-top">
                        <div class="total-score-box">
                            <div class="score-left-top">位列同类型项目前</div>
                            <div class="score-left-bottom">
                                {{ ((1 - peojectRank) * 100).toFixed(1) }}%
                            </div>
                        </div>
                    </div>
                    <div class="score-box-bottom">
                        <div class="chart-box">
                            <img
                                src="../static/faces/cool.png"
                                alt=""
                                style="
                                    width: 170px;
                                    transform: translate(60px, 30px);
                                "
                                v-show="peojectRank >= 0.8"
                            />
                            <img
                                src="../static/faces/good.png"
                                alt=""
                                style="
                                    width: 170px;
                                    transform: translate(60px, 30px);
                                "
                                v-show="peojectRank >= 0.5 && peojectRank < 0.8"
                            />
                            <img
                                src="../static/faces/bad.png"
                                alt=""
                                style="
                                    width: 170px;
                                    transform: translate(60px, 30px);
                                "
                                v-show="peojectRank < 0.5"
                            />
                        </div>
                    </div>
                </div>
                <div class="detailBox">
                    <div class="score-box-top">
                        <div class="total-score-box">
                            <div class="score-left-top">得分详情</div>
                        </div>
                    </div>
                    <div class="detail-score-box">
                        <div class="left">
                            <div
                                class="score-bar"
                                v-for="bar in totalScoreDetails"
                                :key="bar.name"
                            >
                                <div
                                    class="name"
                                    :style="{
                                        width: getPercentage(bar.cnName,askCount),
                                    }"
                                >
                                    <p>{{ bar.cnName }}</p>
                                </div>
                                <div class="name-black">
                                    <p>{{ bar.cnName }}</p>
                                </div>
                                <div
                                    class="score"
                                    :style="{
                                        width: getPercentage(bar.cnName,askCount),
                                    }"
                                >
                                    <p>
                                        {{
                                            (
                                                project.projectBaseInfo[
                                                    bar.name
                                                ] /
                                                getParameterWeight(bar.cnName)
                                            ).toFixed(2)
                                        }}
                                    </p>
                                </div>
                                <div class="score-black">
                                    <p>
                                        {{
                                            (
                                                project.projectBaseInfo[
                                                    bar.name
                                                ] /
                                                getParameterWeight(bar.cnName)
                                            ).toFixed(2)
                                        }}
                                    </p>
                                </div>
                                <div
                                    class="progressBar"
                                    :style="{
                                        width: getPercentage(bar.cnName,askCount),
                                    }"
                                ></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="onTimeDetail">
                <div class="title">项目及时率详情</div>
                <div class="detailView">
                    <div class="top">
                        <div class="left">
                            <div class="subTitle">时间控制情况</div>
                            <div
                                :id="'onTimeLiquidFillChart' + projectId"
                                class="mychart"
                                style="
                                    width: 100%;
                                    height: 240px;
                                    transform: translate(0, 20px);
                                "
                            ></div>
                        </div>
                        <div class="line"></div>
                        <div class="right">
                            <div class="subTitle">项目进程概览图</div>
                            <div
                                :id="'courseChart1' + projectId"
                                class="mychart"
                                style="width: 100%; height: 170px"
                            ></div>
                            <div
                                :id="'courseChart2' + projectId"
                                class="mychart"
                                style="width: 100%; height: 110px"
                            ></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="costTimeDetail">
                <div class="title">人员成本详情</div>
                <div class="detailView">
                    <div class="top">
                        <div class="left">
                            <div class="subTitle">人员成本控制情况</div>
                            <div
                                :id="'costTimeLiquidFillChart' + projectId"
                                class="mychart"
                                style="
                                    width: 100%;
                                    height: 240px;
                                    transform: translate(0, 20px);
                                "
                            ></div>
                        </div>
                        <div class="line"></div>
                        <div class="right">
                            <div class="subTitle">任务执行情况概览图</div>
                            <div
                                :id="'tasksCourseChart' + projectId"
                                class="mychart"
                                style="width: 100%; height: 280px"
                            ></div>
                        </div>
                    </div>
                    <div class="bottom">
                        <div class="left">
                            <div class="subTitle">最超时任务概览</div>
                            <n-data-table
                                pagination-behavior-on-filter="first"
                                :columns="timeCostingColumnsOption"
                                :data="mostTimeCostingData"
                                :pagination="{ pageSize: 6 }"
                            />
                        </div>
                        <div class="line"></div>
                        <div class="right">
                            <div class="subTitle">最省时任务概览</div>
                            <n-data-table
                                pagination-behavior-on-filter="first"
                                :columns="timeCostingColumnsOption"
                                :data="leastTimeCostingData"
                                :pagination="{ pageSize: 6 }"
                            />
                        </div>
                    </div>
                </div>
            </div>
            <div class="defectsDetail">
                <div class="title">代码质量详情</div>
                <div class="detailView">
                    <div class="top">
                        <div class="left">
                            <div class="subTitle">代码缺陷情况</div>
                            <div
                                :id="'defectsTotalChart' + projectId"
                                class="mychart"
                                style="
                                    width: 100%;
                                    height: 340px;
                                    transform: translate(0, -20px);
                                "
                            ></div>
                        </div>
                        <div class="line"></div>
                        <div class="right">
                            <div class="subTitle">缺陷情况概览图</div>
                            <div
                                :id="'defectsCourseChart' + projectId"
                                class="mychart"
                                style="width: 100%; height: 280px"
                            ></div>
                        </div>
                    </div>
                    <div class="bottom">
                        <div class="left">
                            <div class="subTitle">缺陷修复者排行榜</div>
                            <div
                                :id="'defectsFixerChart' + projectId"
                                class="mychart"
                                style="width: 100%; height: 360px"
                            ></div>
                        </div>
                        <div class="line"></div>
                        <div class="right">
                            <div class="subTitle">最耗时缺陷概览</div>
                            <n-data-table
                                pagination-behavior-on-filter="first"
                                :columns="timeCostingDefectsColumnsOption"
                                :data="mostTimeCostingDefectsData"
                                :pagination="{ pageSize: 6 }"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="outerSkeleton" v-show="projectSpinning">
            <div style="display:flex; justify-content: space-between; margin-top:10px;">
                <n-skeleton height="28px" width="80px" style="margin: 10px"/>
                <n-skeleton height="28px" width="80px" style="margin: 10px"/>
            </div>
            <div style="display: flex;">
                <n-skeleton height="360px" width="23%" :sharp="false" style="margin: 5px 10px"/>
                <n-skeleton height="360px" width="23%" :sharp="false" style="margin: 5px 10px"/>
                <n-skeleton height="360px" width="50%" :sharp="false" style="margin: 5px 10px"/>
            </div>
            <div style="margin-top:10px;">
                <n-skeleton height="28px" width="120px" style="margin: 10px"/>
                <n-skeleton height="360px" width="98.5%" :sharp="false" style="margin: 5px 10px"/>
            </div>
            <div style="margin-top:10px;">
                <n-skeleton height="28px" width="120px" style="margin: 10px"/>
                <n-skeleton height="700px" width="98.5%" :sharp="false" style="margin: 5px 10px"/>
            </div>
        </div>
    </div>
</template>

<script>
import { inject, onMounted, getCurrentInstance } from "@vue/runtime-core";
import { h, ref, computed } from "vue";
import { useMessage } from "naive-ui";
import { ExpandOutline, ReaderOutline } from "@vicons/ionicons5";
import { drawTotalScoreChart } from "./charts/totalScore";
import { drawCourseChart } from "./charts/courseChart";
import { drawTasksCourseChart } from "./charts/tasksCourseChart";
import { drawDefectsCourseChart } from "./charts/defectsCourseChart";
import { drawDefectsTotalChart } from "./charts/defectsTotalChart";
import { drawOnTimeLiquidFillChart } from "./charts/onTimeLiquidFill";
import { drawCostTimeLiquidFillChart } from "./charts/costTimeLiquidFill";
import { drawDefectsFixerChart } from "./charts/defectsFixerChart";
import moment from "moment";
export default {
    components: {
        ExpandOutline,
        ReaderOutline,
    },
    props: {
        projectId: Number,
        panelKey: String,
    },
    setup(props, context) {
        let projectId = ref(undefined);
        const project = ref({});
        const projectSpinning = ref(true);
        const $axios = inject("$axios");
        const message = useMessage();
        const $baseURL =
            getCurrentInstance()?.appContext.config.globalProperties.$baseURL;
        const totalScoreDetails = ref([]);
        const tabManager = inject("FreeTabs");

        //最耗时和最省时任务情况
        let mostTimeCostingData = ref([]);
        let leastTimeCostingData = ref([]);
        let timeCostingColumnsOption = ref([
            {
                title: "任务ID",
                key: "taskId",
            },
            {
                title: "任务名",
                key: "taskName",
            },
            {
                title: "计划时间",
                key: "planTime",
            },
            {
                title: "实际时间",
                key: "costTime",
            },
            {
                title: "完成者",
                key: "userName",
                defaultFilterOptionValues: [],
                filterOptions: [],
                filter(value, row) {
                    return !!~row.userName.indexOf(String(value));
                },
            },
        ]);
        let timeCostingDefectsColumnsOption = ref([
            {
                title: "缺陷ID",
                key: "defectId"
            },
            {
                title: "缺陷内容",
                key: "defectName",
                ellipsis: {
                    tooltip: true
                }
            },
            {
                title: "开启时间",
                key: "defectStartTime",
                render (row) {
                    return moment(row.defectStartTime).format("YYYY-MM-DD")
                }
            },
            {
                title: "关闭时间",
                key: "defectEndTime",
                render (row) {
                    return moment(row.defectEndTime).format("YYYY-MM-DD")
                }
            },
            {
                title: "修复者",
                key: "userName",
                defaultFilterOptionValues: [],
                filterOptions: [
                    
                ],
                filter(value, row) {
                    return !!~row.userName.indexOf(String(value));
                }
            }   
        ])
        let mostTimeCostingDefectsData = ref([]);
        let peojectRank = ref(0);

        onMounted(() => {
            projectId.value = props.projectId;
            getProjectBasicInfo();
            getProjectRankInSameAssessScheme();
        });

        //获取得分应占的百分比
        function getMaxThreshold(data, parameterName) {
            let maxThreshold = 0;
            for (let item of data) {
                if (item.parameterName == parameterName) {
                    maxThreshold = Math.max(item.parameterScore, maxThreshold);
                }
            }
            return maxThreshold;
        }

        let askCount = ref(0);
        let askCountInterval = setInterval(()=>{
            askCount.value ++;
            if(!projectSpinning.value) clearInterval(askCountInterval);
        },1500);
        function getPercentage(parameterName,askCount) {
            if(!project.value.projectBaseInfo || askCount == 0 || projectSpinning.value) return "0%";
            if (parameterName == "主管评价") {
                return project.value.projectBaseInfo["additionScore"] / 1 + "%";
            } else {
                let name = undefined;
                for (let item of totalScoreDetails.value) {
                    if (item.cnName == parameterName) {
                        name = item.name;
                        break;
                    }
                }
                if (name != undefined) {
                    for (let item of project.value.schemeInfo
                        .assessParameterList) {
                        if (item.parameterName == parameterName) {
                            return (
                                project.value.projectBaseInfo[name] /
                                    getMaxThreshold(
                                        project.value.schemeInfo
                                            .thresholdVoList,
                                        parameterName
                                    ) /
                                    item.parameterWeight +
                                "%"
                            );
                        }
                    }
                    return "0%";
                } else {
                    return "0%";
                }
            }
        }
        //获得单项的权重
        function getParameterWeight(parameterName) {
            if (parameterName == "主管评价") return 1;
            for (let item of project.value.schemeInfo.assessParameterList) {
                // console.log(item.parameterName);
                if (item.parameterName == parameterName) {
                    return item.parameterWeight;
                }
            }
        }

        //获取项目排名信息
        function getProjectRankInSameAssessScheme() {
            $axios
                .get(
                    $baseURL +
                        "/projectScore/getProjectRankInSameAssessScheme?projectId=" +
                        projectId.value
                )
                .then((res) => {
                    if (res.data.respCode == 200) {
                        peojectRank.value = res.data.data;
                    }
                });
        }

        //获取并处理基本项目信息
        function getProjectBasicInfo() {
            projectSpinning.value = true;
            $axios
                .get(
                    $baseURL +
                        "/project/getProjectDetailById?projectId=" +
                        projectId.value
                )
                .then((res) => {
                    // console.log(res.data);
                    if (res.data.respCode == 200) {
                        //节流，防止请求过快！
                        setTimeout(() => {
                            console.log(res.data.data);
                            project.value = res.data.data;
                            //处理一堆本该由后端处理的基础数据
                            let timeAll = 0;
                            for (let item of project.value.tasks) {
                                timeAll += item.costTime;
                            }
                            project.value.projectBaseInfo.timeAll = timeAll;
                            project.value.projectBaseInfo.actualTime =
                                Math.ceil(
                                    (moment(
                                        moment.utc(
                                            project.value.projectBaseInfo
                                                .actualEndTime
                                        )
                                    ) -
                                        moment(
                                            moment.utc(
                                                project.value.projectBaseInfo
                                                    .actualStartTime
                                            )
                                        )) /
                                        1000 /
                                        60 /
                                        60 /
                                        24
                                );
                            project.value.projectBaseInfo.scheduledTime =
                                Math.ceil(
                                    (moment(
                                        moment.utc(
                                            project.value.projectBaseInfo
                                                .scheduledEndTime
                                        )
                                    ) -
                                        moment(
                                            moment.utc(
                                                project.value.projectBaseInfo
                                                    .scheduledStartTime
                                            )
                                        )) /
                                        1000 /
                                        60 /
                                        60 /
                                        24
                                );

                            //渲染总分行图标信息
                            drawTotalScoreChart(
                                res.data.data,
                                "totalScoreChart" + projectId.value
                            );
                            totalScoreDetails.value = [
                                {
                                    name: "onTimeScore",
                                    fullScore: 2,
                                    cnName: "项目完成及时率",
                                },
                                {
                                    name: "costTimeScore",
                                    fullScore: 2,
                                    cnName: "人员成本",
                                },
                                {
                                    name: "defectScore",
                                    fullScore: 2,
                                    cnName: "代码质量",
                                },
                                {
                                    name: "additionScore",
                                    fullScore: 2,
                                    cnName: "主管评价",
                                },
                            ];

                            //渲染时间控制情况
                            drawCourseChart(
                                res.data.data,
                                "courseChart1" + projectId.value,
                                "courseChart2" + projectId.value
                            );
                            drawOnTimeLiquidFillChart(
                                res.data.data,
                                "onTimeLiquidFillChart" + projectId.value
                            );

                            //渲染人员成本情况
                            drawTasksCourseChart(
                                res.data.data,
                                "tasksCourseChart" + projectId.value
                            );
                            drawCostTimeLiquidFillChart(
                                res.data.data,
                                "costTimeLiquidFillChart" + projectId.value
                            );
                            mostTimeCostingData.value = JSON.parse(
                                JSON.stringify(res.data.data.tasks)
                            );
                            mostTimeCostingData.value.sort((item1, item2) => {
                                return (
                                    item2.costTime / item2.planTime -
                                    item1.costTime / item1.planTime
                                );
                            });
                            leastTimeCostingData.value = JSON.parse(
                                JSON.stringify(res.data.data.tasks)
                            );
                            leastTimeCostingData.value.sort((item1, item2) => {
                                return (
                                    -(item2.costTime / item2.planTime) +
                                    item1.costTime / item1.planTime
                                );
                            });
                            timeCostingDefectsColumnsOption.value[4].filterOptions =
                                Array.from(
                                    new Set(
                                        mostTimeCostingData.value
                                            .map((obj, index) => {
                                                return obj.userName;
                                            })
                                            .join(",")
                                            .split(",")
                                    )
                                );
                            timeCostingDefectsColumnsOption.value[4].filterOptions =
                                timeCostingDefectsColumnsOption.value[4].filterOptions.map(
                                    (obj, index) => {
                                        return {
                                            label: obj,
                                            value: obj,
                                        };
                                    }
                                );

                            //渲染缺陷情况
                            drawDefectsTotalChart(
                                res.data.data,
                                "defectsTotalChart" + projectId.value
                            );
                            drawDefectsCourseChart(
                                res.data.data,
                                "defectsCourseChart" + projectId.value
                            );
                            drawDefectsFixerChart(
                                res.data.data,
                                "defectsFixerChart" + projectId.value
                            );
                            mostTimeCostingDefectsData.value = JSON.parse(
                                JSON.stringify(res.data.data.defects)
                            );
                            mostTimeCostingDefectsData.value.sort(
                                (item1, item2) => {
                                    let startTime1 = moment(
                                        item1.defectStartTime
                                    ).toDate();
                                    let startTime2 = moment(
                                        item2.defectStartTime
                                    ).toDate();
                                    let endTime1 = moment(
                                        item1.defectStartTime
                                    ).toDate();
                                    let endTime2 = moment(
                                        item2.defectEndTime
                                    ).toDate();
                                    return (
                                        -(endTime1 - startTime1) +
                                        (endTime2 - startTime2)
                                    );
                                }
                            );
                            timeCostingColumnsOption.value[4].filterOptions =
                                Array.from(
                                    new Set(
                                        mostTimeCostingDefectsData.value
                                            .map((obj, index) => {
                                                return obj.userName;
                                            })
                                            .join(",")
                                            .split(",")
                                    )
                                );
                            timeCostingColumnsOption.value[4].filterOptions =
                                timeCostingColumnsOption.value[4].filterOptions.map(
                                    (obj, index) => {
                                        return {
                                            label: obj,
                                            value: obj,
                                        };
                                    }
                                );
                        }, 500);
                    } else {
                        message.error("获取项目详细信息失败");
                    }
                })
                .catch((err) => {
                    // console.log(err);
                    message.error("请求失败");
                })
                .then(() => {
                    projectSpinning.value = false;
                });
        }

        return {
            projectId,
            project,
            projectSpinning,
            totalScoreDetails,
            getPercentage,
            getParameterWeight,
            mostTimeCostingData,
            leastTimeCostingData,
            mostTimeCostingDefectsData,
            timeCostingColumnsOption,
            timeCostingDefectsColumnsOption,
            tabManager,
            peojectRank,
            emit: context.emit,
            askCount
        };
    },
};
</script>

<style scoped lang="scss">
.extraOuter {
    padding: 0 15px;
    background-color:#f7f8fa;
    .outer {
        width:100%;
        .topBtns {
            margin: 0 10px;
            display: flex;
            justify-content: space-between;
            width: calc(100% - 20px);
            p {
                font-size: 16px;
            }
        }
        .totalView {
            display: flex;
            .outerBox {
                width: 290px;
                margin: 10px;
                margin-top: 0px;
                height: 360px;
                background-color: white;
                box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.1);
                border-radius: 3px;
                .score-box-top {
                    display: flex;
                    justify-content: space-between;
                    color: #202d40;
                    .total-score-box {
                        margin-left: 25px;
                        margin-top: 24px;
                        width: 180px;
                        .score-left-top {
                            font-size: 18px;
                            font-weight: bold;
                        }
                        .score-left-bottom {
                            font-size: 35px;
                            font-weight: bold;
                            color: #0467d7;
                        }
                    }
                    .collection-right {
                        width: 120px;
                        margin-right: -15px;
                        margin-top: -11px;
                    }
                }
            }
            .scoreRank {
            }
            .detailBox {
                width: calc(100% - 580px - 40px - 20px);
                margin: 10px;
                margin-top: 0px;
                height: 360px;
                background-color: white;
                box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.1);
                border-radius: 3px;
                .score-box-top {
                    display: flex;
                    justify-content: space-between;
                    color: #202d40;
                    .total-score-box {
                        margin-left: 25px;
                        margin-top: 24px;
                        width: 180px;
                        .score-left-top {
                            font-size: 18px;
                            font-weight: bold;
                        }
                        .score-left-bottom {
                            font-size: 35px;
                            font-weight: bold;
                            color: #0467d7;
                        }
                    }
                }
                .detail-score-box {
                    height: calc(100% - 92.8px);
                    display: flex;
                    margin: 12.5px 21px;
                    .left {
                        height: calc(100% - 8.8px);
                        width: 100%;
                        display: flex;
                        flex-wrap: wrap;
                        .score-bar {
                            background-color: #e9eaeb;
                            border-radius: 3px;
                            margin: 8px;
                            height: calc(47.5%);
                            width: calc(50% - 16px);
                            position: relative;
                            .name {
                                font-size: 16px;
                                position: absolute;
                                height: 50px;
                                color: white;
                                overflow: hidden;
                                font-weight: bold;
                                z-index: 1;
                                transform: translateY(4px);
                                width:0;
                                transition:all 1.5s;
                                p {
                                    left: 18px;
                                    position: absolute;
                                    white-space: nowrap;
                                }
                            }
                            .name-black {
                                font-size: 16px;
                                position: absolute;
                                color: #606c80;
                                z-index: 0;
                                transform: translateY(4px);
                                p {
                                    left: 18px;
                                    position: absolute;
                                    white-space: nowrap;
                                }
                            }
                            .score {
                                top: 30px;
                                font-size: 35px;
                                transform: translate(0, -8px);
                                position: absolute;
                                color: white;
                                z-index: 1;
                                overflow: hidden;
                                height: 130px;
                                font-weight: bold;
                                width:0;
                                transition:all 1.5s;
                                p {
                                    left: 18px;
                                    position: absolute;
                                    white-space: nowrap;
                                }
                            }
                            .score-black {
                                top: 30px;
                                font-size: 35px;
                                transform: translate(0, -8px);
                                position: absolute;
                                color: #606c80;
                                z-index: 0;
                                p {
                                    left: 18px;
                                    position: absolute;
                                    white-space: nowrap;
                                }
                            }
                            .progressBar {
                                position: absolute;
                                height: 100%;
                                top: 0;
                                left: 0;
                                border-radius: 3px;
                                background-color: #2e80df;
                                z-index: 0;
                                width:0;
                                transition:all 1.5s;
                            }
                        }
                    }
                    .right {
                        .progress {
                            width: 100%;
                            margin-left: 20px;
                            .n-progress {
                                width: 15vw;
                            }
                        }
                        .des {
                        }
                    }
                }
            }
        }
        .onTimeDetail,
        .costTimeDetail,
        .defectsDetail {
            margin-bottom: 20px;
            .title {
                margin: 10px 10px;
                font-size: 18px;
                font-weight: bold;
                color: #202d40;
            }
            .detailView {
                width: calc(100% - 20px);
                margin: 10px;
                background-color: white;
                box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.1);
                border-radius: 3px;
                .subTitle {
                    font-weight: normal;
                    font-size: 17px;
                    margin: 24px 0 12px 0;
                }
                .line {
                    height: 80%;
                    width: 1px;
                    background-color: #dfe3e9;
                }
                .top {
                    display: flex;
                    align-items: center;
                    width: 100%;
                    height: 360px;
                    .left {
                        width: calc(30% - 40px);
                        height: 100%;
                        margin: 0 20px;
                    }
                    .right {
                        width: calc(70% - 40px);
                        height: 100%;
                        margin: 0 20px;
                    }
                }
                .bottom {
                    width: calc(100%);
                    display: flex;
                    align-items: center;
                    height: 470px;
                    .left {
                        width: calc(50% - 40px);
                        height: 100%;
                        margin: 0 20px;
                    }
                    .right {
                        width: calc(50% - 40px);
                        height: 100%;
                        margin: 0 20px;
                    }
                }
            }
        }
    }
}
</style>