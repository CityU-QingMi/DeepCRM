    public Result resultAction() throws Exception {
    	return new Result() {
            public Configuration configuration;

            @Inject
            public void setConfiguration(Configuration config) {
                this.configuration = config;
            }
            public void execute(ActionInvocation invocation) throws Exception {
                if (configuration != null)
                    resultCalled = true;
            }
    	    
    	};
    }
