    int nextIntRandom(Random r, int range) {

        int b = r.nextInt();

        if (b == Integer.MIN_VALUE) {
            b = Integer.MAX_VALUE;
        }

        b = Math.abs(b);

        return b % range;
    }
