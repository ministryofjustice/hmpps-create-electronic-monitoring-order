# hmpps-create-electronic-monitoring-order
[![repo standards badge](https://img.shields.io/badge/dynamic/json?color=blue&style=flat&logo=github&label=MoJ%20Compliant&query=%24.result&url=https%3A%2F%2Foperations-engineering-reports.cloud-platform.service.justice.gov.uk%2Fapi%2Fv1%2Fcompliant_public_repositories%2Fhmpps-create-electronic-monitoring-order)](https://operations-engineering-reports.cloud-platform.service.justice.gov.uk/public-github-repositories.html#hmpps-create-electronic-monitoring-order "Link to report")
[![CircleCI](https://circleci.com/gh/ministryofjustice/hmpps-create-electronic-monitoring-order/tree/main.svg?style=svg)](https://circleci.com/gh/ministryofjustice/hmpps-create-electronic-monitoring-order)
[![Docker Repository on Quay](https://quay.io/repository/hmpps/hmpps-create-electronic-monitoring-order/status "Docker Repository on Quay")](https://quay.io/repository/hmpps/hmpps-create-electronic-monitoring-order)
[![API docs](https://img.shields.io/badge/API_docs_-view-85EA2D.svg?logo=swagger)](https://hmpps-create-electronic-monitoring-order-dev.hmpps.service.justice.gov.uk/webjars/swagger-ui/index.html?configUrl=/v3/api-docs)

## Running the project on a MoJ mac

### Prerequisites
- Java JDK version 21.0.0 onwards
  - Check version: `java -version`
  - Installation: `brew install java`
- Gradle version 8.5
  - Check version: `gradle -version`
  - Installation: `brew install gradle`
- Kotlin version 1.9.22 
  - Check version: `kotlin -version` (may not run)
  - Installation: `brew install kotlin`'

### Running in VS Code
- Install extensions before cloning the repository, **then restart VS Code**:
  - [Kotlin](https://marketplace.visualstudio.com/items?itemName=fwcd.kotlin)
  - [Gradle for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-gradle)
- Clone the repo:  
  `git clone git@github.com:ministryofjustice/hmpps-create-electronic-monitoring-order.git`
- Open the project in VS Code. It should tell you that gradle config was successful.
- Open the gradle extension using the menu on the left (elephant icon).  
  Run the following tasks by clicking the play button on the line for the task:
  - Tasks/build/build
  - Tasks/application/bootRun

This should run the webserver locally. You should be able to access it at `localhost:8080`.

## Accessing the deployed webservice

Once changes to this repo have been reviewed and merged into main, the service will be automatically redeployed.

The status of the redeployment can be viewed in [CircleCI](https://app.circleci.com/pipelines/github/ministryofjustice/hmpps-create-electronic-monitoring-order).

The service is deployed to this url:  
https://hmpps-create-electronic-monitoring-order-dev.hmpps.service.justice.gov.uk/

The health of the service can be viewed here:  
https://hmpps-create-electronic-monitoring-order-dev.hmpps.service.justice.gov.uk/health

When accessing these endpoints you may receive a 403 error. This is due to an issue with the GlobalProtect VPN provided on MoJ computers.  To resolve this error, open GlobalProtect and refresh your VPN connection. You may need to do this a couple of times.  
This issue is documented in MoJ Slack.
