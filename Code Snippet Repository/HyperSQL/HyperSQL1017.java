    public void writeChars(char[] c, int length) throws IOException {

        for (int i = 0; i < length; i++) {
            int v     = c[i];
            int count = 0;

            tempBuffer[count++] = (byte) (v >>> 8);
            tempBuffer[count++] = (byte) v;

            write(tempBuffer, 0, count);
        }
    }
