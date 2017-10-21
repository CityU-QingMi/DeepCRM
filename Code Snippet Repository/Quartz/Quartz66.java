    public SchedulerMetaData(String schedName, String schedInst,
            Class<?> schedClass, boolean isRemote, boolean started,
            boolean isInStandbyMode, boolean shutdown, Date startTime, int numJobsExec,
            Class<?> jsClass, boolean jsPersistent, boolean jsClustered, Class<?> tpClass, int tpSize,
            String version) {
        this.schedName = schedName;
        this.schedInst = schedInst;
        this.schedClass = schedClass;
        this.isRemote = isRemote;
        this.started = started;
        this.isInStandbyMode = isInStandbyMode;
        this.shutdown = shutdown;
        this.startTime = startTime;
        this.numJobsExec = numJobsExec;
        this.jsClass = jsClass;
        this.jsPersistent = jsPersistent;
        this.jsClustered = jsClustered;
        this.tpClass = tpClass;
        this.tpSize = tpSize;
        this.version = version;
    }
