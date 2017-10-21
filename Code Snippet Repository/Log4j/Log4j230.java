    @Override
    public void putAll(final ReadOnlyStringMap source) {
        if (source == this || source == null || source.isEmpty()) {
            // this.putAll(this) does not modify this collection
            // this.putAll(null) does not modify this collection
            // this.putAll(empty ReadOnlyStringMap) does not modify this collection
            return;
        }
        assertNotFrozen();
        assertNoConcurrentModification();

        if (source instanceof SortedArrayStringMap) {
            if (this.size == 0) {
                initFrom0((SortedArrayStringMap) source);
            } else {
                merge((SortedArrayStringMap) source);
            }
        } else if (source != null) {
            source.forEach(PUT_ALL, this);
        }
    }
