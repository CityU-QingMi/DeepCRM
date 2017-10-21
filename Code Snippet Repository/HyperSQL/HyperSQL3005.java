    public int compareTo(IntervalSecondData b) {

        if (units > b.units) {
            return 1;
        } else if (units < b.units) {
            return -1;
        } else {
            if (nanos > b.nanos) {
                return 1;
            } else if (nanos < b.nanos) {
                return -1;
            } else {
                return 0;
            }
        }
    }
