    public Object[] getData() {

        Object[] data = rowData;

        if (data == null) {
            data = store.getData(this);
            data = rowData;

            this.keepInMemory(false);
        } else {
            accessCount++;
        }

        return data;
    }
