    public void beginAction(Session session, Statement cs) {

        if (session.isTransaction) {
            return;
        }

        if (cs == null) {
            return;
        }

        writeLock.lock();

        try {
            if (hasExpired) {
                session.redoAction = true;

                return;
            }

            cs = updateCurrentStatement(session, cs);

            if (cs == null) {
                return;
            }

            if (session.abortTransaction) {
                return;
            }

            session.isPreTransaction = true;

            if (!isLockedMode && !cs.isCatalogLock(txModel)) {
                return;
            }

            beginActionTPL(session, cs);
        } finally {
            writeLock.unlock();
        }
    }
