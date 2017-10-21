    public boolean remove(long key) {

        int oldSize = size();

        super.addOrRemove(key, 0, null, null, true);

        boolean result = oldSize != size();

        if (result) {
            long[] array = toArray();

            super.clear();

            for (int i = 0; i < array.length; i++) {
                add(array[i]);
            }
        }

        return result;
    }
