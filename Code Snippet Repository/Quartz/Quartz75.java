    public static Date computeEndTimeToAllowParticularNumberOfFirings(OperableTrigger trigg, org.quartz.Calendar cal, 
            int numTimes) {

        OperableTrigger t = (OperableTrigger) trigg.clone();

        if (t.getNextFireTime() == null) {
            t.computeFirstFireTime(cal);
        }
        
        int c = 0;
        Date endTime = null;
        
        for (int i = 0; i < numTimes; i++) {
            Date d = t.getNextFireTime();
            if (d != null) {
                c++;
                t.triggered(cal);
                if(c == numTimes)
                    endTime = d;
            } else {
                break;
            }
        }
        
        if(endTime == null)
            return null;
        
        endTime = new Date(endTime.getTime() + 1000L);
        
        return endTime;
    }
