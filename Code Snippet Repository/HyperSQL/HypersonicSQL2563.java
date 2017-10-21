    public int getLobCount() {

        writeLock.lock();

        try {
            sysLobSession.sessionContext.pushDynamicArguments(new Object[]{});

            Result result = getLobCount.execute(sysLobSession);

            sysLobSession.sessionContext.pop();

            RowSetNavigator navigator = result.getNavigator();
            boolean         next      = navigator.next();

            if (!next) {
                navigator.release();

                return 0;
            }

            Object[] data = navigator.getCurrent();

            return ((Number) data[0]).intValue();
        } finally {
            writeLock.unlock();
        }
    }
