  public String getUUID() {
    if (realJobStore == null) {
      try {
        init();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return realJobStore.getUUID();
  }
