package uk.gov.justice.digital.hmpps.hmppscreateelectronicmonitoringorder.integration

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import java.sql.DriverManager

class DatabaseIntegrationTests {
  private val url = "jdbc:postgresql://localhost:5432/cemo-database"
  private val user = "cemo-database"
  private val password = "cemo-database"

  @Test
  fun `Service can connect to database`() {
    assertDoesNotThrow {
      DriverManager.getConnection(url, user, password)
    }
  }
}
