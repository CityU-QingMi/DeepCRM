  @SuppressWarnings("")
  public void initialize(ClassLoadHelper loadHelper, SchedulerSignaler schedulerSignaler) {
    this.terracottaClientId = clusterInfo.getCurrentNode().getId();
    this.ftrCtr = System.currentTimeMillis();

    // this MUST happen before initializing the trigger set (otherwise we might receive an update which get an NPE)
    // this.serializer.setClassLoadHelper(loadHelper);

    this.signaler = schedulerSignaler;

    getLog().info(getClass().getSimpleName() + " initialized.");

    ((ToolkitInternal) toolkit).registerBeforeShutdownHook(new ShutdownHook(this));
  }
