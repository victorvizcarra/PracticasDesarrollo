job('PracticaDockerDSL') {
    description('Practica de publicación en Docker-hub')
    scm {
        git('https://github.com/victorvizcarra/practicasdesarrollo.git', 'main') { node ->
            node / gitConfigName('victorvizcarra')
            node / gitConfigEmail('victorvizcarra@gmail.com')
        }
    }
    triggers {
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('victorvizcarra/practicasdesarrollo')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('docker-hub')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
    publishers {
	slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
