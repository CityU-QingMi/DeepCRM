    public void writeChars(char[] charArray) {

        int len = charArray.length;

        ensureRoom(len * 2);

        for (int i = 0; i < len; i++) {
            int v = charArray[i];

            buffer[count++] = (byte) (v >>> 8);
            buffer[count++] = (byte) v;
        }
    }
