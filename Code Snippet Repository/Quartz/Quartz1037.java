  @Override
  public void releaseAcquiredTrigger(OperableTrigger trigger) {
    while (!toolkitShutdown) {
      try {
        lock();
        try {
          TriggerWrapper tw = triggerFacade.get(trigger.getKey());
          if (tw != null && trigger.getFireInstanceId().equals(tw.getTriggerClone().getFireInstanceId()) && tw.getState() == TriggerState.ACQUIRED) {
            tw.setState(TriggerState.WAITING, terracottaClientId, triggerFacade);
            timeTriggers.add(tw);
          }
        } finally {
          unlock();
        }
      } catch (RejoinException e) {
        try {
          Thread.sleep(retryInterval);
        } catch (InterruptedException f) {
          throw new IllegalStateException("Received interrupted exception", f);
        }
        continue;
      } catch (JobPersistenceException e) {
        try {
          Thread.sleep(retryInterval);
        } catch (InterruptedException f) {
          throw new IllegalStateException("Received interrupted exception", f);
        }
        continue;
      }
      break;
    }
  }
