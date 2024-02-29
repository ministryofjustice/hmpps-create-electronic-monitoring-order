package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

@Testcontainers
class SQSIntegrationTests {

  companion object {
    @Container
    val localStackContainer: LocalStackContainer =
      LocalStackContainer(DockerImageName.parse("localstack/localstack:2.0.0"))
        .withServices(LocalStackContainer.Service.SQS)

    private val testSqsClient: SqsClient by lazy {
      SqsClient.builder()
        .endpointOverride(localStackContainer.getEndpointOverride(LocalStackContainer.Service.SQS))
        .region(Region.of(localStackContainer.region))
        .build()
    }

    lateinit var queueUrl: String

    @JvmStatic
    @BeforeAll
    fun setup() {
      val createQueueResponse = testSqsClient.createQueue(
        CreateQueueRequest.builder()
          .queueName("test-sqs")
          .build(),
      )

      queueUrl = createQueueResponse.queueUrl()
    }
  }

  @Test
  fun `Service can send a message to SQS`() {
    val messageBody = "Test message"

    val sendMessageRequest = SendMessageRequest.builder()
      .queueUrl(queueUrl)
      .messageBody(messageBody)
      .build()

    assertDoesNotThrow {
      testSqsClient.sendMessage(sendMessageRequest)
    }
  }
}
