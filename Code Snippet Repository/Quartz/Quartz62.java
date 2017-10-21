    private static int translate(IntervalUnit unit) {
        switch(unit) {
            case DAY : return Calendar.DAY_OF_YEAR;
            case HOUR : return Calendar.HOUR_OF_DAY;
            case MINUTE : return Calendar.MINUTE;
            case MONTH : return Calendar.MONTH;
            case SECOND : return Calendar.SECOND;
            case MILLISECOND : return Calendar.MILLISECOND;
            case WEEK : return Calendar.WEEK_OF_YEAR;
            case YEAR : return Calendar.YEAR;
            default : throw new IllegalArgumentException("Unknown IntervalUnit");
        }
    }
