    private static boolean isWildcardAssignable(final WildcardType lhs, final Type rhs) {
        final Type[] lhsUpperBounds = getEffectiveUpperBounds(lhs);
        final Type[] lhsLowerBounds = getEffectiveLowerBounds(lhs);
        if (rhs instanceof WildcardType) {
            // oh boy, this scenario requires checking a lot of assignability!
            final WildcardType rhsType = (WildcardType) rhs;
            final Type[] rhsUpperBounds = getEffectiveUpperBounds(rhsType);
            final Type[] rhsLowerBounds = getEffectiveLowerBounds(rhsType);
            for (final Type lhsUpperBound : lhsUpperBounds) {
                for (final Type rhsUpperBound : rhsUpperBounds) {
                    if (!isBoundAssignable(lhsUpperBound, rhsUpperBound)) {
                        return false;
                    }
                }
                for (final Type rhsLowerBound : rhsLowerBounds) {
                    if (!isBoundAssignable(lhsUpperBound, rhsLowerBound)) {
                        return false;
                    }
                }
            }
            for (final Type lhsLowerBound : lhsLowerBounds) {
                for (final Type rhsUpperBound : rhsUpperBounds) {
                    if (!isBoundAssignable(rhsUpperBound, lhsLowerBound)) {
                        return false;
                    }
                }
                for (final Type rhsLowerBound : rhsLowerBounds) {
                    if (!isBoundAssignable(rhsLowerBound, lhsLowerBound)) {
                        return false;
                    }
                }
            }
        } else {
            // phew, far less bounds to check
            for (final Type lhsUpperBound : lhsUpperBounds) {
                if (!isBoundAssignable(lhsUpperBound, rhs)) {
                    return false;
                }
            }
            for (final Type lhsLowerBound : lhsLowerBounds) {
                if (!isBoundAssignable(lhsLowerBound, rhs)) {
                    return false;
                }
            }
        }
        return true;
    }
