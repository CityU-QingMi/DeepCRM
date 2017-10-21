    public boolean insert(int index,
                          Object key) throws IndexOutOfBoundsException {

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (contains(key)) {
            return false;
        }

        if (index == size()) {
            return add(key);
        }

        Object[] array = new Object[size()];

        toArray(array);
        super.clear();

        for (int i = 0; i < index; i++) {
            add(array[i]);
        }

        add(key);

        for (int i = index; i < array.length; i++) {
            add(array[i]);
        }

        return true;
    }
