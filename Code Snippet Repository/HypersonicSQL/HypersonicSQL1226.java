    synchronized void pushPair(Session session, Object[] row1, Object[] row2) {

        if (maxRowsQueued == 0) {
            session.getInternalConnection();

            try {
                trigger.fire(triggerType, name.name, table.getName().name,
                             row1, row2);
            } finally {
                session.releaseInternalConnection();
            }

            return;
        }

        if (rowsQueued >= maxRowsQueued) {
            if (nowait) {
                pendingQueue.removeLast();    // overwrite last
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {

                    /* ignore and resume */
                }

                rowsQueued++;
            }
        } else {
            rowsQueued++;
        }

        pendingQueue.add(new TriggerData(session, row1, row2));
        notify();    // notify pop's wait
    }
