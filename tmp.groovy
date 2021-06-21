#!groovy
buildType = "maven"

if (buildType == "maven") {
    println('This is a maven project!')
} else if (buildType == "gradle") {
    println("This is a gradle project!")
} else {
    println("Project Type Error!")
}

switch("${buildType}") {
    case "maven":
        println('This is a maven project!')
        break
        ;;
    case "gradle":
        println('This is a gradle project!')
        break
        ;;
    default:
        println("Project Type Error!")
        ;;
}

langs = ['java', 'python', 'groovy', 'golang']

for (lang in langs) {
    if (lang == "java") {
        println("lang error in jaava")
    }
    println("lang is ${lang}")
}

// while(1==1) {
//     println("true")
// }

// function
def PrintMes(value){
    println("foo")
    return value
}

PrintMes("bar")

// regex
@NonCPS
String getBranch(String branchName) {
    def matcher = (branchName =~ "RELEASE-[0-9]{4}")
    if (matcher.find()) {
        newBranchName = matcher[0]
    } else {
        newBranchName = branchName
    }

    newBranchName
}

newBranchName = getBranch(branchName)
println("新分支: ${newBranchName}")


// DSL
def response = readJSON text: "${scanResult}"
println(scanResult)

// native method
import groovy.json.*

@NonCPS
def GetJson(text) {
    def prettyJson = JsonOutput.prttyPrint(text)
    new JsonSlurperClassic().parseText(prettyJson)
}

// credentials
withCredentials([string(credentialsId: "xxx", variable): "sonarToken"]) {
    println(sonarToken)
}

// Git
checkout([$class: 'GitSCM', branches: [[name: "branchName"]],
            doGenerateSubmoduleConfigurations: false,
            extensions: [], submoduleGfc: [],
            userRemoteConfigs: [[credentialsId: "${credentialsId}",
            usrl: "${srcUrl}"]]])

// SVN
checkout([$class: 'SubversionSCM', additionalCredentials: [],
            filterChangelog: false, ignoreDirPropCHanges: false,
            locations: [[credentialsId: "${credentialsId}",
            depthOtion: 'infinity', ignoreExternalsOtion: true,
            remote: "${svnUrll}"]], workspaceUpdater: [$class: 'CheckoutUpdater']])

publishHTML([allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: './report/',
            reportFiles: 'index.html, footer.html',
            reportName: 'HTML Report',
            reportTitles: 'HTML'])

wrap([$class: 'BuildUser']) {
    echo "full name is $BUILD_USER"
    echo "user id is $BUILD_USER_ID"
    echo "user email is $BUILD_USER_EMAIL"
}

ApiUrl = "http://xxxxxx/api/project_branches/list?project=${projectName}"
Result = httpRequest authentication: 'xxxxxx',
            quiet: true,
            contentType: 'APPLICATION_JSON',
            usl: "${ApiRUL}"