    private void setIdentifierProperties() {

        if (token.tokenType == Tokens.X_IDENTIFIER) {
            token.isUndelimitedIdentifier = true;

            if (token.namePrefix == null) {
                token.tokenType = Tokens.getKeywordID(token.tokenString,
                                                      Tokens.X_IDENTIFIER);

                if (token.tokenType == Tokens.X_IDENTIFIER) {
                    token.tokenType = Tokens.getNonKeywordID(token.tokenString,
                            Tokens.X_IDENTIFIER);
                } else {
                    token.isReservedIdentifier = true;
                    token.isCoreReservedIdentifier =
                        Tokens.isCoreKeyword(token.tokenType);
                }
            }
        } else if (token.tokenType == Tokens.X_DELIMITED_IDENTIFIER) {
            token.isDelimitedIdentifier = true;
        }
    }
