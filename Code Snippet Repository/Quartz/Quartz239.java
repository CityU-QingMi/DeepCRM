    private void removeExcludedDay(java.util.Calendar day, boolean isChecked) {
        if (! isChecked &&
            ! isDayExcluded(day)) {
            return;
        }
        
        // Fast way, see if exact day object was already in list
        if (this.excludeDays.remove(day)) {
            return;
        }
        
        int dmonth = day.get(java.util.Calendar.MONTH);
        int dday = day.get(java.util.Calendar.DAY_OF_MONTH);
        
        // Since there is no guarantee that the given day is in the arraylist with the exact same year
        // search for the object based on month and day of month in the list and remove it
        Iterator<java.util.Calendar> iter = excludeDays.iterator();
        while (iter.hasNext()) {
            java.util.Calendar cl = (java.util.Calendar) iter.next();

            if (dmonth != cl.get(java.util.Calendar.MONTH)) {
                continue;
            }

            if (dday != cl.get(java.util.Calendar.DAY_OF_MONTH)) {
                continue;
            }

            day = cl;
            break;
        }
        
        this.excludeDays.remove(day);
    }
