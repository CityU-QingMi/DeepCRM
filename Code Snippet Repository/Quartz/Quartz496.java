    @Override
    public Date getFinalFireTime() {
        if (complete || getEndTime() == null) {
            return null;
        }

        // back up a second from end time
        Date fTime = new Date(getEndTime().getTime() - 1000L);
        // find the next fire time after that
        fTime = getFireTimeAfter(fTime, true);
        
        // the the trigger fires at the end time, that's it!
        if(fTime.equals(getEndTime()))
            return fTime;
        
        // otherwise we have to back up one interval from the fire time after the end time
        
        Calendar lTime = Calendar.getInstance();
        if(timeZone != null)
            lTime.setTimeZone(timeZone);
        lTime.setTime(fTime);
        lTime.setLenient(true);
        
        if(getRepeatIntervalUnit().equals(IntervalUnit.SECOND)) {
            lTime.add(java.util.Calendar.SECOND, -1 * getRepeatInterval());
        }
        else if(getRepeatIntervalUnit().equals(IntervalUnit.MINUTE)) {
            lTime.add(java.util.Calendar.MINUTE, -1 * getRepeatInterval());
        }
        else if(getRepeatIntervalUnit().equals(IntervalUnit.HOUR)) {
            lTime.add(java.util.Calendar.HOUR_OF_DAY, -1 * getRepeatInterval());
        }
        else if(getRepeatIntervalUnit().equals(IntervalUnit.DAY)) {
            lTime.add(java.util.Calendar.DAY_OF_YEAR, -1 * getRepeatInterval());
        }
        else if(getRepeatIntervalUnit().equals(IntervalUnit.WEEK)) {
            lTime.add(java.util.Calendar.WEEK_OF_YEAR, -1 * getRepeatInterval());
        }
        else if(getRepeatIntervalUnit().equals(IntervalUnit.MONTH)) {
            lTime.add(java.util.Calendar.MONTH, -1 * getRepeatInterval());
        }
        else if(getRepeatIntervalUnit().equals(IntervalUnit.YEAR)) {
            lTime.add(java.util.Calendar.YEAR, -1 * getRepeatInterval());
        }

        return lTime.getTime();
    }
