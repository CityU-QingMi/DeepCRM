    public int read() throws java.io.IOException {

        if (available == 0) {
            return -1;
        }

        available--;

        char c = str.charAt(strOffset);

        if (charOffset == 0) {
            charOffset = 1;

            return (c & 0x0000ff00) >> 8;
        } else {
            charOffset = 0;

            strOffset++;

            return c & 0x000000ff;
        }
    }
