    @Override
    public void clear() {
        if (keys == EMPTY) {
            return;
        }
        assertNotFrozen();
        assertNoConcurrentModification();

        Arrays.fill(keys, 0, size, null);
        Arrays.fill(values, 0, size, null);
        size = 0;
    }
