    public long skip(long n) {

        if (pos + n > count) {
            n = count - pos;
        }

        if (n < 0) {
            return 0;
        }

        pos += n;

        return n;
    }
