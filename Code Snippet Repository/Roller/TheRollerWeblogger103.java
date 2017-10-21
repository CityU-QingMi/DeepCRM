    private HitCountQueue() {
        int sleepTime = 3 * RollerConstants.MIN_IN_MS;
        String sleep = WebloggerConfig.getProperty("hitcount.queue.sleepTime", "180");
        
        try {
            // convert input in seconds to ms
            sleepTime = Integer.parseInt(sleep) * RollerConstants.SEC_IN_MS;
        } catch(NumberFormatException nfe) {
            log.warn("Invalid sleep time ["+sleep+"], using default");
        }
        
        // create the hits queue
        this.queue = Collections.synchronizedList(new ArrayList<String>());
        
        // start up a worker to process the hits at intervals
        HitCountProcessingJob job = new HitCountProcessingJob();
        worker = new ContinuousWorkerThread("HitCountQueueProcessor", job, sleepTime);
        worker.start();
    }
