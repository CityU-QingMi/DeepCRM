    @Override
    public Date computeFirstFireTime(org.quartz.Calendar calendar) {
        
      nextFireTime = getFireTimeAfter(new Date(getStartTime().getTime() - 1000L));
      
      // Check calendar for date-time exclusion
      while (nextFireTime != null && calendar != null
              && !calendar.isTimeIncluded(nextFireTime.getTime())) {
          
          nextFireTime = getFireTimeAfter(nextFireTime);
          
          if(nextFireTime == null)
              break;
      
          //avoid infinite loop
          java.util.Calendar c = java.util.Calendar.getInstance();
          c.setTime(nextFireTime);
          if (c.get(java.util.Calendar.YEAR) > YEAR_TO_GIVEUP_SCHEDULING_AT) {
              return null;
          }
      }
      
      return nextFireTime;
    }
