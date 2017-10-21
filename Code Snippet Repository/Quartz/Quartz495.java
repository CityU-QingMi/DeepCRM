    private boolean daylightSavingHourShiftOccurredAndAdvanceNeeded(Calendar newTime, int initialHourOfDay, Date afterTime) {
        if(isPreserveHourOfDayAcrossDaylightSavings() && newTime.get(Calendar.HOUR_OF_DAY) != initialHourOfDay) {
            newTime.set(Calendar.HOUR_OF_DAY, initialHourOfDay);
            if (newTime.get(Calendar.HOUR_OF_DAY) != initialHourOfDay) {
                return isSkipDayIfHourDoesNotExist();
            } else {
                return !newTime.getTime().after(afterTime);
            }
        }
        return false;
    }
