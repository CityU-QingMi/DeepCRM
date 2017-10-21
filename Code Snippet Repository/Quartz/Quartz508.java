    @Override
    public Date getFinalFireTime() {
        Date resultTime;
        if (getEndTime() != null) {
            resultTime = getTimeBefore(new Date(getEndTime().getTime() + 1000l));
        } else {
            resultTime = (cronEx == null) ? null : cronEx.getFinalFireTime();
        }
        
        if ((resultTime != null) && (getStartTime() != null) && (resultTime.before(getStartTime()))) {
            return null;
        } 
        
        return resultTime;
    }
