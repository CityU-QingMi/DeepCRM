    public int precedenceDegree(Type other) {

        if (other.isIntervalType()) {
            int otherIndex = ((IntervalType) other).endPartIndex;

            return otherIndex - endPartIndex;
        }

        return Integer.MIN_VALUE;
    }
