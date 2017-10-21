  private boolean validateAcquired(List<OperableTrigger> result) {
    if (result.isEmpty()) {
      return false;
    } else {
      while (!toolkitShutdown) {
        try {
          lock();
          try {
            for (OperableTrigger ot : result) {
              TriggerWrapper tw = triggerFacade.get(ot.getKey());
              if (!ot.getFireInstanceId().equals(tw.getTriggerClone().getFireInstanceId()) || !TriggerState.ACQUIRED.equals(tw.getState())) {
                return false;
              }
            }
            return true;
          } finally {
            unlock();
          }
        } catch (JobPersistenceException e) {
          try {
            Thread.sleep(retryInterval);
          } catch (InterruptedException f) {
            throw new IllegalStateException("Received interrupted exception", f);
          }
          continue;
        } catch (RejoinException e) {
          try {
            Thread.sleep(retryInterval);
          } catch (InterruptedException f) {
            throw new IllegalStateException("Received interrupted exception", f);
          }
          continue;
        }
      }
      throw new IllegalStateException("Scheduler has been shutdown");
    }
  }
