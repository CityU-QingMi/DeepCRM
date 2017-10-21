    public int[] readIntArray() throws IOException {

        int   size = readInt();
        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            if (!readNull()) {
                data[i] = readInt();
            }
        }

        return data;
    }
