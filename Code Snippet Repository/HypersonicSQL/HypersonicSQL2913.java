    public User addUser(HsqlName name) {

        if (map.containsKey(name.name)) {
            throw Error.error(ErrorCode.X_28503, name.name);
        }

        if (SqlInvariants.isLobsSchemaName(name.name)
                || SqlInvariants.isSystemSchemaName(name.name)) {
            throw Error.error(ErrorCode.X_28502, name.name);
        }

        User g = new User(name, this);

        map.put(name.name, g);

        return g;
    }
