    public int available() {

        long avail = availableLength - currentPosition;

        if (avail > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) avail;
    }
