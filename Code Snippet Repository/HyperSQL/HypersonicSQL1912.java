    public void writeChars(String s) throws IOException {

        int len = s.length();

        for (int i = 0; i < len; i++) {
            int v     = s.charAt(i);
            int count = 0;

            tempBuffer[count++] = (byte) (v >>> 8);
            tempBuffer[count++] = (byte) v;

            write(tempBuffer, 0, count);
        }
    }
