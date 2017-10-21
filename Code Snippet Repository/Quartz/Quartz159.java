    public void setName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException(
                    "Scheduler name cannot be empty.");
        }

        this.name = name;
        
        if (threadName == null) {
            // thread name not already set, use default thread name
            setThreadName(name + "_QuartzSchedulerThread");
        }        
    }
