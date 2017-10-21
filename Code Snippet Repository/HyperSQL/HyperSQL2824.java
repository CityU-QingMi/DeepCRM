    StatementSchema compileCreateUser() {

        HsqlName name;
        String   password;
        Boolean  admin    = Boolean.FALSE;
        Boolean  isDigest = Boolean.FALSE;
        Grantee  grantor  = session.getGrantee();

        read();

        name = readNewUserIdentifier();

        readThis(Tokens.PASSWORD);

        if (readIfThis(Tokens.DIGEST)) {
            isDigest = Boolean.TRUE;
        }

        password = readPassword();

        if (token.tokenType == Tokens.ADMIN) {
            read();

            admin = Boolean.TRUE;
        }

        checkDatabaseUpdateAuthorisation();

        String     sql            = getLastPart();
        Object[]   args           = new Object[] {
            name, password, grantor, admin, isDigest
        };
        HsqlName[] writeLockNames = database.schemaManager.catalogNameArray;

        return new StatementSchema(sql, StatementTypes.CREATE_USER, args,
                                   null, writeLockNames);
    }
