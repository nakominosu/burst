namespace * org.burstsys.gen.thrift.api.client.domain

include "clientTypes.thrift"
include "clientView.thrift"

/** A domain encapsulates a logical dataset on the store side. */
struct BTDomain {
    /** The PK is used internally, but should not be relied upon by clients */
    1: optional i64 pk
    /** The user defined key (UDK) is a unique key that should be used to create/fetch domains */
    2: required string udk
    /** The moniker is a user-friendly display string for this domain */
    3: required string moniker
    /** Domain properties are used to store additional information about the domain */
    4: required map<string, string> domainProperties
    /** Labels allow users to classify domains, but aren't used internally by Burst */
    5: required map<string, string> labels
    /** The create timestamp is generated by Burst when the domain is created */
    6: optional i64 createTimestamp
    /** The modify timestamp is set by Burst when the domain is updated */
    7: optional i64 modifyTimestamp
    /**
     * When a domain is fetched it will include the list of views that exist in the domain.
     * When creating a domain with `ensureDomain`, views included here will be treated
     * as if `ensureView` had been called with their contents. */
    8: optional list<clientView.BTView> views
}

/** A response to a domain-related call */
struct BTDomainResponse {
    /** A summary of the results of the request */
    1: required clientTypes.BTRequestOutcome outcome
    /** Miscellaneous metadata about the request */
    2: required map<string, string> meta
    /** The domain returned by the call, if it exists, and the call succeeded */
    3: optional BTDomain domain
}

