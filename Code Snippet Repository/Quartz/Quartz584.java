  public String generateInstanceId() throws SchedulerException {
    String property = System.getProperty(getSystemPropertyName());
    if(property == null) {
      throw new SchedulerException("No value for '" + SYSTEM_PROPERTY
                                   + "' system property found, please configure your environment accordingly!");
    }
    if(getPrepend() != null)
        property = getPrepend() + property;
    if(getPostpend() != null)
        property = property + getPostpend();
    
    return property;
  }
