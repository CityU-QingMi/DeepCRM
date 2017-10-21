    public Object[] toArray() {

        if (isEmpty()) {
            return emptyObjectArray;
        }

        Object[] array = new Object[size()];

        toArray(array);

        return array;
    }
