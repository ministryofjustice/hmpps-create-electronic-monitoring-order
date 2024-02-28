package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import java.sql.DriverManager

class DatabaseIntegrationTests {
  private val url = "jdbc:postgresql://localhost:5430/testdatabase"
  private val user = "testuser"
  private val password = "testpassword"

  @Test
  fun `Service can connect to database`() {
    assertDoesNotThrow {
      DriverManager.getConnection(url, user, password)
    }
  }
}
