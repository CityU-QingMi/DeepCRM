    public NumberSequence getSequence(String name, String schemaName,
                                      boolean raise) {

        readLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(schemaName);

            if (schema != null) {
                NumberSequence object =
                    (NumberSequence) schema.sequenceList.get(name);

                if (object != null) {
                    return object;
                }
            }

            if (raise) {
                throw Error.error(ErrorCode.X_42501, name);
            }

            return null;
        } finally {
            readLock.unlock();
        }
    }
