/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.relate.dialect

import java.sql.{DriverManager, SQLException}

import org.burstsys.relate.{RelateCustomDbcp2Pool, RelateExceptions, RelatePool, configuration}
import org.burstsys.vitals.errors.safely
import scalikejdbc._

object RelateDerbyDialect extends RelateDialect  {
  val code = 1

  val DuplicateKeyErrNos: Set[String] = Set("23505", "23506.T.1", "23507.S.1")
  /** Best-effort mapping of Derby SQL states to distinguished semantic failings. */
  override def mappedSqlException(e: SQLException): Throwable = {
    if (DuplicateKeyErrNos contains e.getSQLState) RelateExceptions.BurstDuplicateKeyException(e.getMessage, cause = e)
    else e
  }

  override def limitClause(limit: Option[Int]): SQLSyntax = limit.collect({
    case l if l > 0 => sqls"FETCH FIRST $l ROWS ONLY"
  }).getOrElse(sqls"")

  final def jdbcDriver: Class[_] = classOf[org.apache.derby.jdbc.EmbeddedDriver]

  final def jdbcUrl(jdbcHost: String, jdbcPort: Int, databaseName: String): String = {
    s"jdbc:derby:memory:$databaseName;${configuration.burstRelateDerbyConnectionOpts.getOrThrow}"
  }

  final def jdbcSystemUrl(jdbcHost: String, jdbcPort: Int): String = {
    s"jdbc:derby:memory:system;${configuration.burstRelateDerbyConnectionOpts.getOrThrow}"
  }

  final override
  def shutdown(databaseName: String): Unit = {
    try {
      DriverManager.getConnection(s"jdbc:derby:memory:$databaseName;shutdown=true")
    } catch safely {
      case e: SQLException =>
    }
  }

  def pool: RelatePool = RelateCustomDbcp2Pool


}
