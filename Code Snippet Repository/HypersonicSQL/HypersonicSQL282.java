    synchronized void setMinValue(long value) {

        checkInTypeRange(value);

        if (value >= maxValue || currValue < value) {
            throw Error.error(ErrorCode.X_42597);
        }

        minValue = value;
    }
