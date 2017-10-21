    public long elementCount(Session session) {

        if (session != null) {
            Index index = this.indexList[0];

            if (session.database.txManager.isMVRows()) {
                switch (table.getTableType()) {

                    case TableBase.MEMORY_TABLE :
                    case TableBase.CACHED_TABLE :
                    case TableBase.TEXT_TABLE :
                        readLock();

                        try {
                            return index.getNodeCount(session, this);
                        } finally {
                            readUnlock();
                        }
                    default :
                }
            }
        }

        return elementCount();
    }
