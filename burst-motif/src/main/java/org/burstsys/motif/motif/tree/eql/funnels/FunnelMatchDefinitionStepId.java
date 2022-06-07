/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.motif.motif.tree.eql.funnels;

public interface FunnelMatchDefinitionStepId extends FunnelMatchDefinition {
    default long getStepId() {
        return -1;
    }
}
