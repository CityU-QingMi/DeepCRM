        private Row getNextRow() {

            if (nextnode == null) {
                release();

                lastrow = null;

                return null;
            }

            NodeAVL lastnode = nextnode;

            if (single) {
                nextnode = null;
            } else {
                store.readLock();

                try {
                    while (true) {
                        if (reversed) {
                            nextnode = index.last(session, store, nextnode,
                                                  distinctCount);
                        } else {
                            nextnode = index.next(session, store, nextnode,
                                                  distinctCount);
                        }

                        if (nextnode == null) {
                            break;
                        }

                        Row row = nextnode.getRow(store);

                        if (session == null
                                || store.canRead(
                                    session, row,
                                    TransactionManager.ACTION_READ, null)) {
                            break;
                        }
                    }
                } finally {
                    store.readUnlock();
                }
            }

            lastrow = lastnode.getRow(store);

            return lastrow;
        }
