    public ThreadGroup getSchedulerThreadGroup() {
        if (threadGroup == null) {
            threadGroup = new ThreadGroup("QuartzScheduler:"
                    + getSchedulerName());
            if (resources.getMakeSchedulerThreadDaemon()) {
                threadGroup.setDaemon(true);
            }
        }

        return threadGroup;
    }
