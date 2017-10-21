    @Override
    public <T> T[] toArray(final T[] ts) {
        final MutableThreadContextStack result = STACK.get();
        if (result == null) {
            if (ts.length > 0) { // as per the contract of j.u.List#toArray(T[])
                ts[0] = null;
            }
            return ts;
        }
        return result.toArray(ts);
    }
