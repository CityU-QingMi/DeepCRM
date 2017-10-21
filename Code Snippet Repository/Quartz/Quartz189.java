        public UserTransactionWithContext() throws SchedulerException {
            try {
                context = new InitialContext();
            } catch (Throwable t) {
                throw new SchedulerException(
                    "UserTransactionHelper failed to create InitialContext to lookup/create UserTransaction.", t);
            }
            
            try {
                userTransaction = (UserTransaction)context.lookup(userTxURL);
            } catch (Throwable t) {
                closeContext();
                throw new SchedulerException(
                    "UserTransactionHelper could not lookup/create UserTransaction.",
                    t);
            }
            
            if (userTransaction == null) {
                closeContext();
                throw new SchedulerException(
                    "UserTransactionHelper could not lookup/create UserTransaction from the InitialContext.");
            }
        }
