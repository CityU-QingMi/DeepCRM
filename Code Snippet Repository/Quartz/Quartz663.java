    public void testExclude() {
        AnnualCalendar annualCalendar = new AnnualCalendar();
        Calendar day = Calendar.getInstance();

        day.set(Calendar.MONTH, 9);
        day.set(Calendar.DAY_OF_MONTH, 15);
        annualCalendar.setDayExcluded(day, false);

        assertTrue("The day 15 October is not expected to be excluded but it is", !annualCalendar.isDayExcluded(day));

        day.set(Calendar.MONTH, 9);
        day.set(Calendar.DAY_OF_MONTH, 15);
        annualCalendar.setDayExcluded((Calendar) day.clone(), true);

        day.set(Calendar.MONTH, 10);
        day.set(Calendar.DAY_OF_MONTH, 12);
        annualCalendar.setDayExcluded((Calendar) day.clone(), true);

        day.set(Calendar.MONTH, 8);
        day.set(Calendar.DAY_OF_MONTH, 1);
        annualCalendar.setDayExcluded((Calendar) day.clone(), true);

        assertTrue("The day 15 October is expected to be excluded but it is not", annualCalendar.isDayExcluded(day));

        day.set(Calendar.MONTH, 9);
        day.set(Calendar.DAY_OF_MONTH, 15);
        annualCalendar.setDayExcluded((Calendar) day.clone(), false);

        assertTrue("The day 15 October is not expected to be excluded but it is", !annualCalendar.isDayExcluded(day));
    }
