   private void runXaResourceRollback() {
      Collection<XAResource> resources = getEnlistedResources();
      for (XAResource res : resources) {
         try {
            res.rollback(xid);
         } catch (XAException e) {
            log.warn("Error while rolling back",e);
         }
      }
   }
