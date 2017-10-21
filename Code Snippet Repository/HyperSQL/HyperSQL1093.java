    public void setSize(int newSize) {

        if (newSize == 0) {
            clear();

            return;
        }

        if (newSize <= elementCount) {
            for (int i = newSize; i < elementCount; i++) {
                elementData[i] = null;
            }

            elementCount = newSize;

            return;
        }

        for (; newSize > elementData.length; ) {
            increaseCapacity();
        }

        elementCount = newSize;
    }
