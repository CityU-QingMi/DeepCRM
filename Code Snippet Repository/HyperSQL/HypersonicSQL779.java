    public SchemaObject getCharacterSet(Session session, String name,
                                        String schemaName) {

        if (schemaName == null
                || SqlInvariants.INFORMATION_SCHEMA.equals(schemaName)) {
            if (name.equals("SQL_IDENTIFIER")) {
                return Charset.SQL_IDENTIFIER_CHARSET;
            }

            if (name.equals("SQL_TEXT")) {
                return Charset.SQL_TEXT;
            }

            if (name.equals("LATIN1")) {
                return Charset.LATIN1;
            }

            if (name.equals("ASCII_GRAPHIC")) {
                return Charset.ASCII_GRAPHIC;
            }
        }

        if (schemaName == null) {
            schemaName = session.getSchemaName(null);
        }

        return getSchemaObject(name, schemaName, SchemaObject.CHARSET);
    }
