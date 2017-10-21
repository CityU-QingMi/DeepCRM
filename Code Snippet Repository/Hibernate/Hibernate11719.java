   private boolean runXaResourcePrepare() throws SystemException {
      Collection<XAResource> resources = getEnlistedResources();
      for (XAResource res : resources) {
         try {
            res.prepare(xid);
         } catch (XAException e) {
            log.trace("The resource wants to rollback!", e);
            return false;
         } catch (Throwable th) {
            log.error("Unexpected error from resource manager!", th);
            throw new SystemException(th.getMessage());
         }
      }
      return true;
   }
