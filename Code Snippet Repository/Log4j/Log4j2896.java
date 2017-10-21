    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        assertNotFrozen();
        assertNoConcurrentModification();

        size = 0;
        containsNullKey = false;
        Arrays.fill(keys, (null));
        Arrays.fill(values, null);
    }
