    @Override
    public void trim(final int depth) {
        if (depth < 0) {
            throw new IllegalArgumentException("Maximum stack depth cannot be negative");
        }
        final MutableThreadContextStack values = STACK.get();
        if (values == null) {
            return;
        }
        final MutableThreadContextStack copy = (MutableThreadContextStack) values.copy();
        copy.trim(depth);
        copy.freeze();
        STACK.set(copy);
    }
