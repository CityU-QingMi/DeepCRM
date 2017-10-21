    synchronized public long getValue() {

        if (limitReached) {
            throw Error.error(ErrorCode.X_2200H);
        }

        long nextValue;

        if (increment > 0) {
            if (currValue > maxValue - increment) {
                if (isCycle) {
                    nextValue = minValue;
                } else {
                    limitReached = true;
                    nextValue    = minValue;
                }
            } else {
                nextValue = currValue + increment;
            }
        } else {
            if (currValue < minValue - increment) {
                if (isCycle) {
                    nextValue = maxValue;
                } else {
                    limitReached = true;
                    nextValue    = minValue;
                }
            } else {
                nextValue = currValue + increment;
            }
        }

        long result = currValue;

        currValue = nextValue;

        return result;
    }
