   private boolean runXaResourceCommitTx() throws HeuristicMixedException {
      Collection<XAResource> resources = getEnlistedResources();
      for (XAResource res : resources) {
         try {
            res.commit(xid, false);//todo we only support one phase commit for now, change this!!!
         } catch (XAException e) {
            log.warn("exception while committing",e);
            throw new HeuristicMixedException(e.getMessage());
         }
      }
      return true;
   }
