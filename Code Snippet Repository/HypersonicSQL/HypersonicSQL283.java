    synchronized void setMaxValue(long value) {

        checkInTypeRange(value);

        if (value <= minValue || currValue > value) {
            throw Error.error(ErrorCode.X_42597);
        }

        maxValue = value;
    }
