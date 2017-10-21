    public void setSize(int newSize) {

        if (!canChangeSize) {
            throw new UnsupportedOperationException("BitMap");
        }

        ensureCapacity(newSize);

        // if newSize is smaller
        if (limitPos > newSize) {
            unsetRange(newSize, limitPos - newSize);

            limitPos = newSize;
        }
    }
