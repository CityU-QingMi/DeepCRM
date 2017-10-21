    synchronized long userUpdate(long value) {

        if (value == currValue) {
            currValue += increment;

            return value;
        }

        if (increment > 0) {
            if (value > currValue) {
                currValue += ((value - currValue + increment) / increment)
                             * increment;
            }
        } else {
            if (value < currValue) {
                currValue += ((value - currValue + increment) / increment)
                             * increment;
            }
        }

        return value;
    }
