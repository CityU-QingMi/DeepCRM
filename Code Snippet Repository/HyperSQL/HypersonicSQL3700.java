    public int compareTo(IntervalMonthData b) {

        if (units > b.units) {
            return 1;
        } else if (units < b.units) {
            return -1;
        } else {
            return 0;
        }
    }
