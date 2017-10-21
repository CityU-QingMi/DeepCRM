    public boolean isSatisfiedBy(final Date date) {
        final Calendar testDateCal = Calendar.getInstance(getTimeZone());
        testDateCal.setTime(date);
        testDateCal.set(Calendar.MILLISECOND, 0);
        final Date originalDate = testDateCal.getTime();

        testDateCal.add(Calendar.SECOND, -1);

        final Date timeAfter = getTimeAfter(testDateCal.getTime());

        return ((timeAfter != null) && (timeAfter.equals(originalDate)));
    }
