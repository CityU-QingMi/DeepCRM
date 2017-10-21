    public int indexOf(Object o) {

        for (int i = 0, size = size(); i < size; i++) {
            Object current = get(i);

            if (current == null) {
                if (o == null) {
                    return i;
                }
            } else if (current.equals(o)) {
                return i;
            }
        }

        return -1;
    }
