    public boolean insert(int index, Object key,
                          Object value) throws IndexOutOfBoundsException {

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        int lookup = getLookup(key, key.hashCode());

        if (lookup >= 0) {
            return false;
        }

        if (index == size()) {
            return add(key, value);
        }

        HashMappedList hm = new HashMappedList(size());

        for (int i = index; i < size(); i++) {
            hm.add(getKey(i), get(i));
        }

        for (int i = size() - 1; i >= index; i--) {
            remove(i);
        }

        for (int i = 0; i < hm.size(); i++) {
            add(hm.getKey(i), hm.get(i));
        }

        return true;
    }
