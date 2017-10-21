    @Override
    public boolean removeAll(final Collection<?> objects) {
        if (!useStack || objects.isEmpty()) {
            return false;
        }
        final MutableThreadContextStack values = STACK.get();
        if (values == null || values.isEmpty()) {
            return false;
        }
        final MutableThreadContextStack copy = (MutableThreadContextStack) values.copy();
        final boolean result = copy.removeAll(objects);
        copy.freeze();
        STACK.set(copy);
        return result;
    }
