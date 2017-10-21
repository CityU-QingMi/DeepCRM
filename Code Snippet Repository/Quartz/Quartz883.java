    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        JobDataMap data = context.getMergedJobDataMap();
        
        String command = data.getString(PROP_COMMAND);

        String parameters = data.getString(PROP_PARAMETERS);

        if (parameters == null) {
            parameters = "";
        }

        boolean wait = true;
        if(data.containsKey(PROP_WAIT_FOR_PROCESS)) {
            wait = data.getBooleanValue(PROP_WAIT_FOR_PROCESS);
        }
        boolean consumeStreams = false;
        if(data.containsKey(PROP_CONSUME_STREAMS)) {
            consumeStreams = data.getBooleanValue(PROP_CONSUME_STREAMS);
        }
            
        Integer exitCode = this.runNativeCommand(command, parameters, wait, consumeStreams);
        context.setResult(exitCode);
        
    }
