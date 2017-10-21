    public int canMoveFrom(Type otherType) {

        if (otherType == this) {
            return 0;
        }

        if (typeCode == otherType.typeCode) {
            return scale >= otherType.scale ? 0
                                            : -1;
        }

        if (!otherType.isIntervalType()) {
            return -1;
        }

        if (isYearMonth == ((IntervalType) otherType).isYearMonth) {
            if (scale < otherType.scale) {
                return -1;
            }

            if (endPartIndex >= ((IntervalType) otherType).endPartIndex) {
                if (precision >= otherType.precision) {
                    if (startPartIndex
                            <= ((IntervalType) otherType).startPartIndex) {
                        return 0;
                    }
                }

                return 1;
            }
        }

        return -1;
    }
