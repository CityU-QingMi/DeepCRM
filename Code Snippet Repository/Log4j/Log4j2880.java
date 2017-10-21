    @Override
    public void putAll(final ReadOnlyStringMap source) {
        assertNotFrozen();
        assertNoConcurrentModification();

        if (size() == 0 && source instanceof OpenHashStringMap) {
            initFrom0((OpenHashStringMap) source);
        } else if (source != null) {
            source.forEach(PUT_ALL, this);
        }
    }
