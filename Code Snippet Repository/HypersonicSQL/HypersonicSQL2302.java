    public int countSet(int pos, int count) {

        int set = 0;

        for (int i = pos; i < pos + count; i++) {
            if (isSet(i)) {
                set++;
            }
        }

        return set;
    }
