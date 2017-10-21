    public int indexOf(Object value) {

        for (int i = 0; i < elementCount; i++) {
            int index = firstindex + i;

            if (index >= list.length) {
                index -= list.length;
            }

            if (list[index] == value) {
                return i;
            }

            if (value != null && value.equals(list[index])) {
                return i;
            }
        }

        return -1;
    }
