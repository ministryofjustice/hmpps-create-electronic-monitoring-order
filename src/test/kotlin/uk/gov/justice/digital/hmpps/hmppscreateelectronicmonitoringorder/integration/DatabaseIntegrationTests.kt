package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.sql.DriverManager

@Testcontainers
class DatabaseIntegrationTests {
  companion object {
    @JvmStatic
    @Container
    val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:16.2")
      .apply {
        withDatabaseName("testdatabase")
        withUsername("testuser")
        withPassword("testpassword")
      }
  }

  private val url: String = postgresContainer.jdbcUrl
  private val user: String = postgresContainer.username
  private val password: String = postgresContainer.password

  @Test
  fun `Service can connect to database`() {
    assertDoesNotThrow {
      DriverManager.getConnection(url, user, password)
    }
  }
}
