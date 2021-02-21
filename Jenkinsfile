#!groovy

@Library('jenkinslib') _

def tools = new org.devops.tools()

String workspace = "/var/lib/jenkins"

// pipeline
pipeline {
    agent any

    options {
        timestamps()  // 日志有时间戳
        skipDefaultCheckout()  // 删除隐式 checkout scm 语句
        disableConcurrentBuilds()  // 禁止并行
        timeout(time: 1, unit: 'HOURS')  // 流水线超时设置 1h
    }

    parameters { 
        string(name: 'DEPLOY_ENV', defaultValue: 'staging', description: '')
    }

    stages {
        // 下载代码
        stage('GetCOde') { // 阶段名称
            steps { // 步骤
                timeout(time:5, unit: 'MINUTES') {
                    script { // 填写运行代码
                        mattermostSend (
                            color: "#2A42EE", 
                            message: "Build STARTED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                        )
                        println('获取代码')
                    }
                }
            }
        }

        stage("ParallelExample") {
            failFast true
            parallel {
                // 代码扫描
        stage('CodeScan') {
            steps {
                timeout(time:20, unit: 'MINUTES') {
                    script {
                        mattermostSend (
                            color: "#2A42EE", 
                            message: "Build STARTED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                        )
                        println('代码扫描')

                        tools.PrintMes("This is my lib!")
                    }
                }
            }
        }

        // 构建
        stage('Build') {
            steps {
                timeout(time:20, unit: 'MINUTES') {
                    script {
                        mattermostSend (
                            color: "#2A42EE", 
                            message: "Build STARTED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                        )
                        println('应用构建')
                    }
                }
            }
        }
            }
        }
    }

    // 构建后操作
    post {
        always {
            script {
                println('always')
            }
        }

        success {
            script {
                currentBuild.description = "构建成功"
            }
        }

        failure {
            script {
                currentBuild.description = "构建失败"
            }
        }

        aborted {
            script {
                currentBuild.description = "构建取消"
            }
        }
    }
}
