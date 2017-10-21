    public Type findDomainOrUDT(Session session, String name, String prefix,
                                String prePrefix, String prePrePrefix) {

        readLock.lock();

        try {
            Type type = (Type) findSchemaObject(session, name, prefix,
                                                prePrefix, SchemaObject.TYPE);

            return type;
        } finally {
            readLock.unlock();
        }
    }
