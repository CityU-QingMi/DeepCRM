    public void reset() {

        cache.writeLock.lock();

        try {
            Iterator it = spaceManagerList.values().iterator();

            while (it.hasNext()) {
                TableSpaceManagerBlocks tableSpace =
                    (TableSpaceManagerBlocks) it.next();

                tableSpace.reset();
            }
        } finally {
            cache.writeLock.unlock();
        }
    }
