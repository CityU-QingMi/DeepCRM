    public CachedObject get(CachedObject object, PersistentStore store,
                            boolean keep) {

        if (object == null) {
            return null;
        }

        writeLock.lock();

        try {
            CachedObject existing = cache.get(object.getPos());

            if (existing != null) {
                return object;
            }

            try {
                buffer.reset(object.getStorageSize());
                dataFile.seek(object.getPos());
                dataFile.read(buffer.getBuffer(), 0, object.getStorageSize());
                buffer.setSize(object.getStorageSize());

                String rowString =
                    buffer.toString(textFileSettings.charEncoding);

                ((RowInputText) rowIn).setSource(rowString, object.getPos(),
                                                 buffer.size());
                store.get(object, rowIn);
                cache.put(object);

                return object;
            } catch (Throwable t) {
                database.logger.logSevereEvent(dataFileName
                                               + " getFromFile problem "
                                               + object.getPos(), t);
                cache.clearUnchanged();
                System.gc();

                return object;
            }
        } finally {
            writeLock.unlock();
        }
    }
