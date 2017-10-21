    boolean scanIntervalSign() {

        boolean negate = false;

        if (intervalPosition == intervalString.length()) {
            return false;
        }

        if (intervalString.charAt(intervalPosition) == '-') {
            negate = true;

            intervalPosition++;
        } else if (intervalString.charAt(intervalPosition) == '+') {
            intervalPosition++;
        }

        return negate;
    }
