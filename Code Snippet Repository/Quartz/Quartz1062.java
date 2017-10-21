  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
    result = prime * result + (int) (scheduledFireTime ^ (scheduledFireTime >>> 32));
    result = prime * result + (int) (fireTime ^ (fireTime >>> 32));
    result = prime * result + ((triggerKey == null) ? 0 : triggerKey.hashCode());
    return result;
  }
