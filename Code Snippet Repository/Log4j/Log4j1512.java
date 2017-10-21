    private long findMinIncrement() {
        if (seconds.size() != 1) {
            return minInSet(seconds) * 1000;
        } else if (seconds.first() == ALL_SPEC_INT) {
            return 1000;
        }
        if (minutes.size() != 1) {
            return minInSet(minutes) * 60000;
        } else if (minutes.first() == ALL_SPEC_INT) {
            return 60000;
        }
        if (hours.size() != 1) {
            return minInSet(hours) * 3600000;
        } else if (hours.first() == ALL_SPEC_INT) {
            return 3600000;
        }
        return 86400000;
    }
