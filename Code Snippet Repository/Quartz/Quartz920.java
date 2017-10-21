    public void initialize(String name, final Scheduler scheduler, ClassLoadHelper schedulerFactoryClassLoadHelper)
        throws SchedulerException {
        super.initialize(name, scheduler);
        this.classLoadHelper = schedulerFactoryClassLoadHelper;
        
        getLog().info("Registering Quartz Job Initialization Plug-in.");
        
        // Create JobFile objects
        StringTokenizer stok = new StringTokenizer(fileNames, FILE_NAME_DELIMITERS);
        while (stok.hasMoreTokens()) {
            final String fileName = stok.nextToken();
            final JobFile jobFile = new JobFile(fileName);
            jobFiles.put(fileName, jobFile);         
        }
    }
