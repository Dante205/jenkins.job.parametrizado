job('ejemplo2-job-DSL') {
  description('My first job DSL de ejemplo')
  scm {
        git('https://github.com/Dante205/jenkins.job.parametrizado.git', 'main') { node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DanteDSL')
            node / gitConfigEmail('dante@dante.com')
        }
    }
  parameters {
    	stringParam('nombre', 'Dante', 'Parametro de cadena nombre')
        choiceParam('planeta', ['Tierra (default)', 'Mercurio', 'Venus', 'Jupiter', 'Marte'])
    	booleanParam('agente', false)
    }
  triggers {
        cron('H/7 * * * *')
    }
  steps {
        shell('bash jobscript.sh')
    }
  publishers {
        mailer('danteaguilar100@gmail.com', true, true)
        slackNotifier {
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(false)
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
