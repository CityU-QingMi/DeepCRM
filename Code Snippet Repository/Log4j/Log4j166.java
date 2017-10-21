    @Override
    public boolean add(final String s) {
        if (!useStack) {
            return false;
        }
        final MutableThreadContextStack copy = getNonNullStackCopy();
        copy.add(s);
        copy.freeze();
        STACK.set(copy);
        return true;
    }
