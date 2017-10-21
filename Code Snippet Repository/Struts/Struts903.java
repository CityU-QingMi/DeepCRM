    public int substract()
        throws IOException {

        if ((end - start) == 0) {
            if (in == null) {
                return -1;
            }
            int n = in.realReadChars(buff, end, buff.length - end);
            if (n < 0) {
                return -1;
            }
        }

        return (buff[start++]);

    }
