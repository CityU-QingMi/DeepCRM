    protected boolean lessThan(int i, int j) {

        if (sortOnValues) {
            if (values[i] < values[j]) {
                return true;
            } else if (values[i] > values[j]) {
                return false;
            }
        }

        if (keys[i] < keys[j]) {
            return true;
        }

        return false;
    }
