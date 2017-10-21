  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    FiredTrigger other = (FiredTrigger) obj;
    if (clientId == null) {
      if (other.clientId != null) return false;
    } else if (!clientId.equals(other.clientId)) return false;
    if (scheduledFireTime != other.scheduledFireTime) return false;
    if (fireTime != other.fireTime) return false;
    if (triggerKey == null) {
      if (other.triggerKey != null) return false;
    } else if (!triggerKey.equals(other.triggerKey)) return false;
    return true;
  }
