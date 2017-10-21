    public Grantee getRole(String name) {

        Grantee g = (Grantee) roleMap.get(name);

        if (g == null) {
            throw Error.error(ErrorCode.X_0P000, name);
        }

        return g;
    }
