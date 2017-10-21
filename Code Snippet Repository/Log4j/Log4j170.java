    @Override
    public boolean remove(final Object o) {
        if (!useStack) {
            return false;
        }
        final MutableThreadContextStack values = STACK.get();
        if (values == null || values.size() == 0) {
            return false;
        }
        final MutableThreadContextStack copy = (MutableThreadContextStack) values.copy();
        final boolean result = copy.remove(o);
        copy.freeze();
        STACK.set(copy);
        return result;
    }
