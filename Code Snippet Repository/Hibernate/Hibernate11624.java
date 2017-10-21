   public Connection getConnection() throws SQLException {
      DualNodeJtaTransactionImpl currentTransaction = DualNodeJtaTransactionManagerImpl
               .getInstance(nodeId).getCurrentTransaction();
      if (currentTransaction == null) {
         isTransactional = false;
         return actualConnectionProvider.getConnection();
      } else {
         isTransactional = true;
         Connection connection = currentTransaction.getEnlistedConnection();
         if (connection == null) {
            connection = actualConnectionProvider.getConnection();
            currentTransaction.enlistConnection(connection);
         }
         return connection;
      }
   }
