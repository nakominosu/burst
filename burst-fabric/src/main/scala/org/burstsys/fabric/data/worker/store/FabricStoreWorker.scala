/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.fabric.data.worker.store

import org.burstsys.fabric.data.model.store.FabricStore

/**
 * worker side processing and management for data stores
 */
trait FabricStoreWorker extends FabricStore with FabricWorkerLoader {

}
