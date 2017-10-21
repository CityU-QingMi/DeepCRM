    void newStore(long requiredPosition) throws IOException {

        long currentPosition = 0;

        if (store == null) {
            preNio = requiredPosition <= database.logger.propNioMaxSize;
        } else {
            currentPosition = store.getFilePointer();

            store.synch();
            store.close();
        }

        if (preNio && initialMaxLength <= requiredPosition) {
            try {
                store = new RAFileNIO(database.logger, fileName, isReadOnly,
                                      requiredPosition,
                                      database.logger.propNioMaxSize);

                store.seek(currentPosition);

                preNio = false;
                isNio  = true;

                return;
            } catch (Throwable e) {
                preNio = false;

                // log event
            }
        }

        isNio = false;
        store = new RAFile(database.logger, fileName, isReadOnly, true, false);

        store.seek(currentPosition);
    }
