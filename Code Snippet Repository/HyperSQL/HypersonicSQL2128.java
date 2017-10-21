    public boolean put(Object key, long value) {

        if (key == null) {
            throw new NoSuchElementException();
        }

        int oldSize = size();

        super.addOrRemove(0, value, key, null, false);

        return oldSize != size();
    }
