    public void write(char[] c, int off, int len) {

        ensureRoom(len * 2);

        for (int i = off; i < len; i++) {
            int v = c[i];

            buffer[count++] = (byte) (v >>> 8);
            buffer[count++] = (byte) v;
        }
    }
