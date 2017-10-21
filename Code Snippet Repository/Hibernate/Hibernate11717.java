   public void rollback() throws IllegalStateException, SystemException {
      status = Status.STATUS_ROLLING_BACK;
      runXaResourceRollback();
      status = Status.STATUS_ROLLEDBACK;

      if (connection != null) {
         try {
            connection.rollback();
            connection.close();
         } catch (SQLException sqle) {
            status = Status.STATUS_UNKNOWN;
            throw new SystemException();
         }
      }
      
      if (synchronizations != null) {
         for (int i = 0; i < synchronizations.size(); i++) {
            Synchronization s = (Synchronization) synchronizations.get(i);
            if (s != null)
               s.afterCompletion(status);
         }
      }

      // status = Status.STATUS_NO_TRANSACTION;
      jtaTransactionManager.endCurrent(this);
   }
