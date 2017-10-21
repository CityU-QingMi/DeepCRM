  private boolean validateFiring(List<TriggerFiredResult> result) {
    if (result.isEmpty()) {
      return false;
    } else {
      while (!toolkitShutdown) {
        try {
          lock();
          try {
            for (TriggerFiredResult tfr : result) {
              TriggerFiredBundle tfb = tfr.getTriggerFiredBundle();
              if (tfb != null && !triggerFacade.containsFiredTrigger(tfb.getTrigger().getFireInstanceId())) {
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
