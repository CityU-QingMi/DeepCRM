    Type readMysEnum() {

        read();
        checkIsThis(Tokens.OPENBRACKET);

        HsqlName name = database.nameManager.newHsqlName("ENUM", false,
            SchemaObject.DOMAIN);
        Type t = Type.getType(Types.SQL_VARCHAR, null, null, 32, 0);
        UserTypeModifier m = new UserTypeModifier(name, SchemaObject.DOMAIN,
            t);

        t.userTypeModifier = m;

        return t;
    }
