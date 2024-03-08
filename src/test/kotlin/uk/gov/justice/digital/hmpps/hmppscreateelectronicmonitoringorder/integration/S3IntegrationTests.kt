package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest
import software.amazon.awssdk.services.s3.model.HeadBucketRequest
import java.net.URI

class S3IntegrationTests {

  companion object {

    private val testS3Client: S3Client = S3Client.builder()
      .forcePathStyle(true)
      .endpointOverride(URI.create("http://localhost:4566"))
      .region(Region.EU_WEST_1)
      .build()

    private const val BUCKET_NAME = "cemo-s3"

    @JvmStatic
    @BeforeAll
    fun createBucket() {
      testS3Client.createBucket(CreateBucketRequest.builder().bucket(BUCKET_NAME).build())
    }

    @JvmStatic
    @AfterAll
    fun deleteBucket() {
      testS3Client.deleteBucket(DeleteBucketRequest.builder().bucket(BUCKET_NAME).build())
    }
  }

  @Test
  fun `Service can access the s3 bucket`() {
    val headBucketRequest = HeadBucketRequest.builder()
      .bucket(BUCKET_NAME)
      .build()

    assertDoesNotThrow { testS3Client.headBucket(headBucketRequest) }
  }
}
