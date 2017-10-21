    public int precedenceDegree(Type other) {

        if (other.isNumberType()) {
            int otherWidth = ((NumberType) other).typeWidth;

            return otherWidth - typeWidth;
        }

        return Integer.MIN_VALUE;
    }
