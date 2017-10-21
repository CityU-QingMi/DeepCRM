    Collation readCollateClauseOrNull() {

        if (token.tokenType == Tokens.COLLATE) {
            read();

            Collation collation = database.schemaManager.getCollation(session,
                token.tokenString, token.namePrefix);

            return collation;
        }

        return null;
    }
