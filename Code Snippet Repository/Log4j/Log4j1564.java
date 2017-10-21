    private static boolean isParameterizedAssignable(final ParameterizedType lhs, final ParameterizedType rhs) {
        if (lhs.equals(rhs)) {
            // that was easy
            return true;
        }
        final Type[] lhsTypeArguments = lhs.getActualTypeArguments();
        final Type[] rhsTypeArguments = rhs.getActualTypeArguments();
        final int size = lhsTypeArguments.length;
        if (rhsTypeArguments.length != size) {
            // clearly incompatible types
            return false;
        }
        for (int i = 0; i < size; i++) {
            // verify all type arguments are assignable
            final Type lhsArgument = lhsTypeArguments[i];
            final Type rhsArgument = rhsTypeArguments[i];
            if (!lhsArgument.equals(rhsArgument) &&
                !(lhsArgument instanceof WildcardType &&
                    isWildcardAssignable((WildcardType) lhsArgument, rhsArgument))) {
                return false;
            }
        }
        return true;
    }
