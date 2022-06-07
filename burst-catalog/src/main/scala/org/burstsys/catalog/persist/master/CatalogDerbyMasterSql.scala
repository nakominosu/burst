/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.catalog.persist.master

import org.burstsys.relate.TableCreateSql
import scalikejdbc._

trait CatalogDerbyMasterSql {

  self: CatalogMasterPersister =>

  // TODO these unique constraints are prolly not wanted

  def derbyCreateTableSql: TableCreateSql =
    sql"""
     CREATE TABLE  ${this.table} (
       ${this.column.pk} BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
       ${this.column.nodeName} VARCHAR(255) NOT NULL,
       ${this.column.nodeAddress} VARCHAR(255) NOT NULL,
       ${this.column.masterPort} INT NOT NULL,
       ${this.column.siteFk} BIGINT NOT NULL,
       ${this.column.cellFk} BIGINT,
       ${this.column.labels} VARCHAR(32672),
       ${this.column.moniker} VARCHAR(255) NOT NULL,
       ${this.column.masterProperties} VARCHAR(32672),
       UNIQUE (${this.column.moniker})
     )
     """


}
