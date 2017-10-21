   public synchronized static void cleanupTransactions() {
      for (java.util.Iterator it = INSTANCES.values().iterator(); it.hasNext();) {
         TransactionManager tm = (TransactionManager) it.next();
         try {
            tm.suspend();
         } catch (Exception e) {
            log.error("Exception cleaning up TransactionManager " + tm);
         }
      }
   }
