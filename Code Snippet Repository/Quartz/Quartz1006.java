  public DefaultClusteredJobStore(boolean synchWrite, Toolkit toolkit, String jobStoreName,
                                  ToolkitDSHolder toolkitDSHolder, WrapperFactory wrapperFactory) {
    this.toolkit = toolkit;
    this.wrapperFactory = wrapperFactory;
    this.clusterInfo = toolkit.getClusterInfo();

    this.toolkitDSHolder = toolkitDSHolder;

    this.jobFacade = new JobFacade(toolkitDSHolder);
    this.triggerFacade = new TriggerFacade(toolkitDSHolder);

    this.timeTriggers = toolkitDSHolder.getOrCreateTimeTriggerSet();
    this.calendarsByName = toolkitDSHolder.getOrCreateCalendarWrapperMap();

    this.lockType = synchWrite ? ToolkitLockTypeInternal.SYNCHRONOUS_WRITE : ToolkitLockTypeInternal.WRITE;
    ToolkitTransactionType txnType = synchWrite ? ToolkitTransactionType.SYNC : ToolkitTransactionType.NORMAL;
    this.lock = new TransactionControllingLock((ToolkitInternal) toolkit, toolkitDSHolder.getLock(lockType), txnType);

    this.logger = LoggerFactory.getLogger(getClass());

    getLog().info("Synchronous write locking is [" + synchWrite + "]");
  }
