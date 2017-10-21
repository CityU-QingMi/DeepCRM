    synchronized void rollbackNoCheck(boolean chain) {

        if (isClosed) {
            return;
        }

        if (isTransaction) {
            database.txManager.rollback(this);
        }

        endTransaction(false, chain);
    }
