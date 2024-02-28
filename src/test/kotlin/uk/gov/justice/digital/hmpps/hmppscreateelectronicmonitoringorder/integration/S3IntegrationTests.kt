package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration


import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.HeadBucketRequest
import java.net.URI


class S3IntegrationTests {
  private val testS3Client: S3Client = S3Client.builder()
    .forcePathStyle(true)
    .endpointOverride(URI.create("http://localhost:4566"))
    .region(Region.EU_WEST_1)
    .build()

  @Test
  fun `Service can access the s3 bucket`() {
    val headBucketRequest = HeadBucketRequest.builder()
      .bucket("cemo-test-s3")
      .build()

    assertDoesNotThrow{testS3Client.headBucket(headBucketRequest)}
  }
}
