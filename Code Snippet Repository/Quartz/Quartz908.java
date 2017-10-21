    private UserTransaction startUserTransaction() {
        if (wrapInUserTransaction == false) {
            return null;
        }
        
        UserTransaction userTransaction = null;
        try {
            userTransaction = UserTransactionHelper.lookupUserTransaction();
            userTransaction.begin();
        } catch (Throwable t) {
            UserTransactionHelper.returnUserTransaction(userTransaction);
            userTransaction = null;
            getLog().error("Failed to start UserTransaction for plugin: " + getName(), t);
        }
        
        return userTransaction;
    }
