    public boolean isDayExcluded(java.util.Calendar day) {

        if (day == null) {
            throw new IllegalArgumentException(
                    "Parameter day must not be null");
        }

         // Check baseCalendar first
        if (! super.isTimeIncluded(day.getTime().getTime())) {
         return true;
        } 
        
        int dmonth = day.get(java.util.Calendar.MONTH);
        int dday = day.get(java.util.Calendar.DAY_OF_MONTH);

        if (dataSorted == false) {
            Collections.sort(excludeDays, new CalendarComparator());
            dataSorted = true;
        }

        Iterator<java.util.Calendar> iter = excludeDays.iterator();
        while (iter.hasNext()) {
            java.util.Calendar cl = (java.util.Calendar) iter.next();

            // remember, the list is sorted
            if (dmonth < cl.get(java.util.Calendar.MONTH)) {
                return false;
            }

            if (dday != cl.get(java.util.Calendar.DAY_OF_MONTH)) {
                continue;
            }

            if (dmonth != cl.get(java.util.Calendar.MONTH)) {
                continue;
            }

            return true;
        }

        return false;
    }
