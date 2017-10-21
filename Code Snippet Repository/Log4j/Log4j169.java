    @Override
    public String pop() {
        if (!useStack) {
            return Strings.EMPTY;
        }
        final MutableThreadContextStack values = STACK.get();
        if (values == null || values.size() == 0) {
            // Like version 1.2
            return Strings.EMPTY;
        }
        final MutableThreadContextStack copy = (MutableThreadContextStack) values.copy();
        final String result = copy.pop();
        copy.freeze();
        STACK.set(copy);
        return result;
    }
