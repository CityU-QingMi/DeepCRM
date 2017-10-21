        @Override
        public void run() {
            try {

                // Interrupt the job here - using Scheduler API that gets propagated to Job's interrupt
                getLog().info("Interrupting Job as it ran more than the configured max time. Job Details [" + jobKey.getName() + ":" + jobKey.getGroup()+"]");
                scheduler.interrupt(jobKey);
            } catch (SchedulerException x) {
                getLog().info("Error interrupting Job: " + x.getMessage(), x);
            }
        }
