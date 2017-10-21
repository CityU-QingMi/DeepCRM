    synchronized void checkValues() {

        if (restartValueDefault) {
            currValue           = lastValue = startValue;
            restartValueDefault = false;
        }

        if (minValue >= maxValue || startValue < minValue
                || startValue > maxValue || currValue < minValue
                || currValue > maxValue) {
            throw Error.error(ErrorCode.X_42597);
        }
    }
