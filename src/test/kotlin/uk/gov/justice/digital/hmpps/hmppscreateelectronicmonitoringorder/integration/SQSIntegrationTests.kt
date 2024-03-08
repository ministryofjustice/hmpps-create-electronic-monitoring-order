package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import java.net.URI

class SQSIntegrationTests {
  companion object {
    private val testSqsClient: SqsClient = SqsClient.builder()
      .endpointOverride(URI.create("http://localhost:4566"))
      .region(Region.EU_WEST_1)
      .build()

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
    val messageBody = "Message Body"

    val sendMessageRequest = SendMessageRequest.builder()
      .queueUrl(queueUrl)
      .messageBody(messageBody)
      .build()

    assertDoesNotThrow {
      testSqsClient.sendMessage(sendMessageRequest)
    }
  }
}
