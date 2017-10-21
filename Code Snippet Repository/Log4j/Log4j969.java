    @Override
    public int compare(final Class<?> lhs, final Class<?> rhs) {
        final Order lhsOrder = Objects.requireNonNull(lhs, "lhs").getAnnotation(Order.class);
        final Order rhsOrder = Objects.requireNonNull(rhs, "rhs").getAnnotation(Order.class);
        if (lhsOrder == null && rhsOrder == null) {
            // both unannotated means equal priority
            return 0;
        }
        // if only one class is @Order-annotated, then prefer that one
        if (rhsOrder == null) {
            return -1;
        }
        if (lhsOrder == null) {
            return 1;
        }
        // larger value means higher priority
        return Integer.signum(rhsOrder.value() - lhsOrder.value());
    }
