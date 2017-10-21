    public void testForInfiniteLoop() {
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar();

        for(int i=1; i<9; i++) {
            monthlyCalendar.setDayExcluded(i, true);
        }
        Calendar c = Calendar.getInstance();
        c.set(2007, 11, 8, 12, 0, 0);

        monthlyCalendar.getNextIncludedTime(c.getTime().getTime());
    }
