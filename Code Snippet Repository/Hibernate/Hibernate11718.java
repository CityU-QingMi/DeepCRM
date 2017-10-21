   public boolean enlistResource(XAResource xaResource) throws RollbackException, IllegalStateException,
            SystemException {
      enlistedResources.add(new WrappedXaResource(xaResource));
      try {
         xaResource.start(xid, 0);
      } catch (XAException e) {
         log.error("Got an exception", e);
         throw new SystemException(e.getMessage());
      }
      return true;
   }
