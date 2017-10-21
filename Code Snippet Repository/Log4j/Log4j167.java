    @Override
    public boolean addAll(final Collection<? extends String> strings) {
        if (!useStack || strings.isEmpty()) {
            return false;
        }
        final MutableThreadContextStack copy = getNonNullStackCopy();
        copy.addAll(strings);
        copy.freeze();
        STACK.set(copy);
        return true;
    }
