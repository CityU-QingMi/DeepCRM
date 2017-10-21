    static int getHexValue(int c) {

        if (c >= '0' && c <= '9') {
            c -= '0';
        } else if (c > 'z') {
            c = 16;
        } else if (c >= 'a') {
            c -= ('a' - 10);
        } else if (c > 'Z') {
            c = 16;
        } else if (c >= 'A') {
            c -= ('A' - 10);
        } else {
            c = -1;
        }

        return c;
    }
