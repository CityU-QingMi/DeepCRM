    @Override
    public Date getFinalFireTime() {
        if (complete || getEndTime() == null) {
            return null;
        }
        
        // We have an endTime, we still need to check to see if there is a endTimeOfDay if that's applicable.
        Date eTime = getEndTime();
        if (endTimeOfDay != null) {
            Date endTimeOfDayDate = endTimeOfDay.getTimeOfDayForDate(eTime);
            if (eTime.getTime() < endTimeOfDayDate.getTime()) {
                eTime = endTimeOfDayDate;
            }
        }        
        return eTime;
    }
