    private static int getNibble(int value) {

        if (value >= '0' && value <= '9') {
            return value - '0';
        }

        if (value >= 'a' && value <= 'f') {
            return 10 + value - 'a';
        }

        if (value >= 'A' && value <= 'F') {
            return 10 + value - 'A';
        }

        return -1;
    }
