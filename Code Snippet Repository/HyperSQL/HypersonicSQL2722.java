    public void delete(Session session, Row row) {

        writeLock();

        try {
            for (int i = 0; i < indexList.length; i++) {
                indexList[i].delete(session, this, row);
            }

            for (int i = 0; i < subStores.length; i++) {
                subStores[i].delete(session, row);
            }

            row.delete(this);

            long count = elementCount.decrementAndGet();

            if (count > 16 * 1024 && count < baseElementCount / 2) {
                baseElementCount = count;
                searchCost       = null;
            }
        } finally {
            writeUnlock();
        }
    }
