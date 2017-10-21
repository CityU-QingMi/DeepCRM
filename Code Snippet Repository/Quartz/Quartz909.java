    private void resolveUserTransaction(UserTransaction userTransaction) {
        if (userTransaction != null) {
            try {
                if (userTransaction.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
                    userTransaction.rollback();
                } else {
                    userTransaction.commit();
                } 
            } catch (Throwable t) {
                getLog().error("Failed to resolve UserTransaction for plugin: " + getName(), t);
            } finally {
                UserTransactionHelper.returnUserTransaction(userTransaction);
            }
        }
    }
