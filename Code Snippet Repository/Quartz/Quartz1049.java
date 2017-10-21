  public Toolkit buildToolkit() throws IllegalStateException {
    if (tcConfigTypeStatus.getState() == TCConfigTypeState.INIT) {
      //
      throw new IllegalStateException(
                                      "Please set the tcConfigSnippet or tcConfigUrl before attempting to create client");
    }
    Properties properties = new Properties();
    properties.setProperty(TC_TUNNELLED_MBEAN_DOMAIN_KEY, getTunnelledDomainCSV());
    properties.setProperty(TC_REJOIN_KEY, Boolean.toString(isRejoin()));
    switch (tcConfigTypeStatus.getState()) {
      case TC_CONFIG_SNIPPET:
        properties.setProperty(TC_CONFIG_SNIPPET_KEY, tcConfigTypeStatus.getTcConfigSnippet());
        return createToolkit("toolkit:terracotta:", properties);
      case TC_CONFIG_URL:
        return createToolkit("toolkit:terracotta://" + tcConfigTypeStatus.getTcConfigUrl(), properties);
      default:
        throw new IllegalStateException("Unknown tc config type - " + tcConfigTypeStatus.getState());
    }
  }
