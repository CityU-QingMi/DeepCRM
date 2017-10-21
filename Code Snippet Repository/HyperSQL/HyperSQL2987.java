    public static int subtractMonths(Session session, TimestampData a,
                                     TimestampData b, boolean isYear) {

        Calendar calendar = session.getCalendarGMT();

        synchronized (calendar) {
            boolean negate = false;

            if (b.getSeconds() > a.getSeconds()) {
                negate = true;

                TimestampData temp = a;

                a = b;
                b = temp;
            }

            calendar.setTimeInMillis(a.getSeconds() * 1000);

            int months = calendar.get(Calendar.MONTH);
            int years  = calendar.get(Calendar.YEAR);

            calendar.setTimeInMillis(b.getSeconds() * 1000);

            months -= calendar.get(Calendar.MONTH);
            years  -= calendar.get(Calendar.YEAR);

            if (isYear) {
                months = years * 12;
            } else {
                if (months < 0) {
                    months += 12;

                    years--;
                }

                months += years * 12;
            }

            if (negate) {
                months = -months;
            }

            return months;
        }
    }
