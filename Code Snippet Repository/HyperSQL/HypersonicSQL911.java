    public synchronized void removeSchemaReference(Schema schema) {

        Iterator it = sessionMap.values().iterator();

        for (int i = 0; it.hasNext(); i++) {
            Session session = (Session) it.next();

            if (session.getCurrentSchemaHsqlName() == schema.getName()) {
                session.resetSchema();
            }
        }
    }
