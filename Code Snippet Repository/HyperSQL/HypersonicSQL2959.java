    public synchronized void removeSchemaReference(String schemaName) {

        for (int i = 0; i < userList.size(); i++) {
            User     user   = (User) userList.get(i);
            HsqlName schema = user.getInitialSchema();

            if (schema == null) {
                continue;
            }

            if (schemaName.equals(schema.name)) {
                user.setInitialSchema(null);
            }
        }
    }
