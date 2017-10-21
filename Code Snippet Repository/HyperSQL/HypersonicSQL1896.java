    void ensureRoom(int size) {

        if (size <= buffer.length) {
            return;
        }

        int newSize = buffer.length;

        while (newSize < size) {
            newSize *= 2;
        }

        char[] newBuffer = new char[newSize];

        System.arraycopy(buffer, 0, newBuffer, 0, count);

        buffer = newBuffer;
    }
