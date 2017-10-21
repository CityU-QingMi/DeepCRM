    public void run() {
        
        // we only run once
        if (this.job != null) {
            // process job
            try {
                this.job.execute();
            } catch(Exception e) {
                // oops
                log.error("Error executing job. "+
                        "Worker = "+this.id+", "+
                        "Job = "+this.job.getClass().getName(), e);
            } finally {
                // since this is a thread we have to make sure that we tidy up ourselves
                Weblogger roller = WebloggerFactory.getWeblogger();
                roller.release();
            }
        }
        
    }
