    @Override
    public int compare(JobDetailImpl o1, JobDetailImpl o2) {
      return o1.getName().equals(o2.getName()) && o1.getGroup().equals(o2.getGroup())
              && ((o1.getDescription() == null && o2.getDescription() == null) || o1.getDescription().equals(o2.getDescription()))
              && o1.getJobClass().equals(o2.getJobClass())
              && o1.getJobDataMap().equals(o2.getJobDataMap())
              && o1.isDurable() == o2.isDurable()
              && o1.requestsRecovery() == o2.requestsRecovery() 
              ? 0 : -1;
    }
