    public void writeChars(String s) {

        int len = s.length();

        ensureRoom(len * 2);

        for (int i = 0; i < len; i++) {
            int v = s.charAt(i);

            buffer[count++] = (byte) (v >>> 8);
            buffer[count++] = (byte) v;
        }
    }
