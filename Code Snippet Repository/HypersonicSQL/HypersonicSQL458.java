    SimpleName readSimpleName() {

        checkIsSimpleName();

        SimpleName name = HsqlNameManager.getSimpleName(token.tokenString,
            isDelimitedIdentifier());

        read();

        return name;
    }
