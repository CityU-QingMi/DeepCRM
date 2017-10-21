    public static Date nextGivenMinuteDate(Date date, int minuteBase) {
        if (minuteBase < 0 || minuteBase > 59) {
            throw new IllegalArgumentException(
                    "minuteBase must be >=0 and <= 59");
        }

        if (date == null) {
            date = new Date();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setLenient(true);

        if (minuteBase == 0) {
            c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 1);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            return c.getTime();
        }

        int minute = c.get(Calendar.MINUTE);

        int arItr = minute / minuteBase;

        int nextMinuteOccurance = minuteBase * (arItr + 1);

        if (nextMinuteOccurance < 60) {
            c.set(Calendar.MINUTE, nextMinuteOccurance);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            return c.getTime();
        } else {
            c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 1);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            return c.getTime();
        }
    }
