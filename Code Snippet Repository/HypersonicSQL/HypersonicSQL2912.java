    public Grantee addRole(HsqlName name) {

        if (map.containsKey(name.name)) {
            throw Error.error(ErrorCode.X_28503, name.name);
        }

        if (SqlInvariants.isLobsSchemaName(name.name)
                || SqlInvariants.isSystemSchemaName(name.name)) {
            throw Error.error(ErrorCode.X_28502, name.name);
        }

        Grantee g = new Grantee(name, this);

        g.isRole = true;

        map.put(name.name, g);
        roleMap.add(name.name, g);

        return g;
    }
