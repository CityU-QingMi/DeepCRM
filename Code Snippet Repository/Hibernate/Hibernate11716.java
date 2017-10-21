   public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
            IllegalStateException, SystemException {

      if (status == Status.STATUS_MARKED_ROLLBACK) {
         log.trace("on commit, status was marked for rollback-only");
         rollback();
      } else {
         status = Status.STATUS_PREPARING;

         if ( synchronizations != null ) {
            for ( int i = 0; i < synchronizations.size(); i++ ) {
               Synchronization s = (Synchronization) synchronizations.get( i );
               s.beforeCompletion();
            }
         }
         
         if (!runXaResourcePrepare()) {
            status = Status.STATUS_ROLLING_BACK;
         } else {
            status = Status.STATUS_PREPARED;
         }

         status = Status.STATUS_COMMITTING;

         if (connection != null) {
            try {
               connection.commit();
               connectionProvider.closeConnection(connection);
               connection = null;
            } catch (SQLException sqle) {
               status = Status.STATUS_UNKNOWN;
               throw new SystemException();
            }
         }
         
         runXaResourceCommitTx();

         status = Status.STATUS_COMMITTED;

         if ( synchronizations != null ) {
            for ( int i = 0; i < synchronizations.size(); i++ ) {
               Synchronization s = (Synchronization) synchronizations.get( i );
               s.afterCompletion( status );
            }
         }

         // status = Status.STATUS_NO_TRANSACTION;
         jtaTransactionManager.endCurrent(this);
      }
   }
