    public int indexOf(Object o) {

        for (int i = 0; i < elementCount; i++) {
            if (elementData[i] == o) {
                return i;
            }
        }

        return -1;
    }
