  private void init() throws SchedulerConfigException {
    if (realJobStore != null) { return; }

    if ((tcConfig != null) && (tcConfigUrl != null)) {
      //
      throw new SchedulerConfigException("Both " + TC_CONFIG_PROP + " and " + TC_CONFIGURL_PROP
                                         + " are set in your properties. Please define only one of them");
    }

    if ((tcConfig == null) && (tcConfigUrl == null)) {
      //
      throw new SchedulerConfigException("Neither " + TC_CONFIG_PROP + " or " + TC_CONFIGURL_PROP
                                         + " are set in your properties. Please define one of them");
    }

    final boolean isURLConfig = tcConfig == null;
    TerracottaToolkitBuilder toolkitBuilder = new TerracottaToolkitBuilder();
    if (isURLConfig) {
      toolkitBuilder.setTCConfigUrl(tcConfigUrl);
    } else {
      toolkitBuilder.setTCConfigSnippet(tcConfig);
    }
    if (rejoin != null) {
      toolkitBuilder.setRejoin(rejoin);
    }
    toolkitBuilder.addTunnelledMBeanDomain("quartz");
    toolkit = (ToolkitInternal) toolkitBuilder.buildToolkit();

    try {
      realJobStore = getRealStore(toolkit);
    } catch (Exception e) {
      throw new SchedulerConfigException("Unable to create Terracotta client", e);
    }
  }
