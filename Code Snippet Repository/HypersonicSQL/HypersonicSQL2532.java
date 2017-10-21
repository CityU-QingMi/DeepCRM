    public void read(RowInputInterface in) {

        this.position = in.getFilePosition();

        int capacity = values.length;

        for (int i = 0; i < capacity; i++) {
            values[i] = in.readInt();
        }

        hasChanged = false;
    }
