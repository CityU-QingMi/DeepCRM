    public void executeInForeground(Runnable runnable)
            throws InterruptedException {
        Future task = serviceScheduler.submit(runnable);
        
        // since this task is really meant to be executed within this calling 
        // thread, here we can add a little code here to loop until it realizes 
        // the task is done
        while(!task.isDone()) {
            Thread.sleep(RollerConstants.HALF_SEC_IN_MS);
        }
    }
