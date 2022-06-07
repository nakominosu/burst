/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.motif.motif.tree.data;

import org.burstsys.motif.paths.Path;

/**
 * Bind a path to its appropriate Schema meta-data
 */
public interface SegmentBinding {
    Path getPath();
}
