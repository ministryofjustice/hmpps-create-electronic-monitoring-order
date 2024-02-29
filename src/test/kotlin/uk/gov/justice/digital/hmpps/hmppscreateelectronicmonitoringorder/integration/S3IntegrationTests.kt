package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest
import software.amazon.awssdk.services.s3.model.HeadBucketRequest


@Testcontainers
class S3IntegrationTests {
  companion object {
    private const val BUCKET_NAME = "test-bucket"

    @Container
    val localStackContainer: LocalStackContainer = LocalStackContainer(DockerImageName.parse("localstack/localstack:2.0.0"))
      .withServices(LocalStackContainer.Service.S3)

    val testS3Client: S3Client by lazy {
      S3Client.builder()
        .endpointOverride(localStackContainer.getEndpointOverride(LocalStackContainer.Service.S3))
        .credentialsProvider { AwsBasicCredentials.create(localStackContainer.accessKey, localStackContainer.secretKey) }
        .region(Region.of(localStackContainer.region))
        .build()
    }

    @JvmStatic
    @BeforeAll
    fun createBucket() {
      testS3Client.createBucket(CreateBucketRequest.builder().bucket(BUCKET_NAME).build())
    }
  }

  @Test
  fun `Service can access the s3 bucket`() {
    val headBucketRequest = HeadBucketRequest.builder()
      .bucket(BUCKET_NAME)
      .build()

    assertDoesNotThrow {
      testS3Client.headBucket(headBucketRequest)
    }
  }
}
