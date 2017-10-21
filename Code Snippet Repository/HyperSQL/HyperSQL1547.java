    public void read(RowInputInterface in) {

        this.position = in.getFilePosition();

        int[] array    = bitMap.getIntArray();
        int   capacity = array.length;

        for (int i = 0; i < capacity; i++) {
            array[i] = in.readInt();
        }

        hasChanged = false;
    }
