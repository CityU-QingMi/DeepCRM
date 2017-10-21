    protected Date getAdjustedTime(Date startTime, String changeFactor) {
        
        if(startTime == null || changeFactor == null) {
            return startTime;
        }
        
        Date adjustedTime = startTime;
        
        if("startOfDay".equals(changeFactor)) {
            adjustedTime = DateUtil.getEndOfDay(startTime);
        } else if("startOfHour".equals(changeFactor)) {
            adjustedTime = DateUtil.getEndOfHour(startTime);
        }
        
        return adjustedTime;
    }
