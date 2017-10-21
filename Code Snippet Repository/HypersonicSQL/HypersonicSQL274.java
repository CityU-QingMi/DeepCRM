    synchronized long systemUpdate(long value) {

        if (value == currValue) {
            currValue += increment;

            return value;
        }

        if (increment > 0) {
            if (value > currValue) {
                currValue = value + increment;
            }
        } else {
            if (value < currValue) {
                currValue = value + increment;
            }
        }

        return value;
    }
