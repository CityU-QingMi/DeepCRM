    public int indexOf(Object o) {

        if (o == null) {
            for (int i = 0; i < elementCount; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }

            return -1;
        }

        for (int i = 0; i < elementCount; i++) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }

        return -1;
    }
