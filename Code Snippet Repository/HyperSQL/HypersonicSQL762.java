    public Table getUserTable(String name, String schema) {

        Table t = findUserTable(name, schema);

        if (t == null) {
            String longName = schema == null ? name
                                             : schema + '.' + name;

            throw Error.error(ErrorCode.X_42501, longName);
        }

        return t;
    }
