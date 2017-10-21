    public void writeBytes(String s) {

        int len = s.length();

        ensureRoom(len);

        for (int i = 0; i < len; i++) {
            buffer[count++] = (byte) s.charAt(i);
        }
    }
