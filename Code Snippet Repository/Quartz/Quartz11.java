    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        // This job simply prints out its job name and the
        // date and time that it is running
        JobKey jobKey = context.getJobDetail().getKey();
        
        // Grab and print passed parameters
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String favoriteColor = data.getString(FAVORITE_COLOR);
        int count = data.getInt(EXECUTION_COUNT);
        _log.info("ColorJob: " + jobKey + " executing at " + new Date() + "\n" +
            "  favorite color is " + favoriteColor + "\n" + 
            "  execution count (from job map) is " + count + "\n" + 
            "  execution count (from job member variable) is " + _counter);
        
        // increment the count and store it back into the 
        // job map so that job state can be properly maintained
        count++;
        data.put(EXECUTION_COUNT, count);
        
        // Increment the local member variable 
        // This serves no real purpose since job state can not 
        // be maintained via member variables!
        _counter++;
    }
