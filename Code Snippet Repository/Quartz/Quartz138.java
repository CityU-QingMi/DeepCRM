    public void setJobFactory(JobFactory factory) throws SchedulerException {

        if(factory == null) {
            throw new IllegalArgumentException("JobFactory cannot be set to null!");
        }

        getLog().info("JobFactory set to: " + factory);

        this.jobFactory = factory;
    }
