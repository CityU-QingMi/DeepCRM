    static boolean referenceEquals(Reference r, Object o) {
        // compare reference to reference.
        if (o instanceof InternalReference) {
            // are they the same reference? used in cleanup.
            if (o == r) {
                return true;
            }

            // do they reference identical values? used in conditional puts.
            Object referent = ((Reference) o).get();
            return referent != null && referent == r.get();
        }

        // is the wrapped object identical to the referent? used in lookups.
        return ((ReferenceAwareWrapper) o).unwrap() == r.get();
    }
