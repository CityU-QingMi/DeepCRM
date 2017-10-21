    private void checkIncrementRange(int incr, int type, int idxPos) throws ParseException {
        if (incr > 59 && (type == SECOND || type == MINUTE)) {
            throw new ParseException("Increment > 60 : " + incr, idxPos);
        } else if (incr > 23 && (type == HOUR)) {
            throw new ParseException("Increment > 24 : " + incr, idxPos);
        } else if (incr > 31 && (type == DAY_OF_MONTH)) {
            throw new ParseException("Increment > 31 : " + incr, idxPos);
        } else if (incr > 7 && (type == DAY_OF_WEEK)) {
            throw new ParseException("Increment > 7 : " + incr, idxPos);
        } else if (incr > 12 && (type == MONTH)) {
            throw new ParseException("Increment > 12 : " + incr, idxPos);
        }
    }
