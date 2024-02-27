package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration.aws

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import java.net.URI

class SQSIntegrationTests {
  private val testSqsClient: SqsClient = SqsClient.builder()
    .endpointOverride(URI.create("http://localhost:4566"))
    .region(Region.EU_WEST_1)
    .build()

  @Test
  fun `Service can send a message to SQS`() {
    val queueUrl = "http://localhost:4566/000000000000/cemo-test-sqs"
    val messageBody = "Message Body"

    val sendMessageRequest = SendMessageRequest.builder()
      .queueUrl(queueUrl)
      .messageBody(messageBody)
      .build()

    assertDoesNotThrow{
      testSqsClient.sendMessage(sendMessageRequest)
    }
  }
}
