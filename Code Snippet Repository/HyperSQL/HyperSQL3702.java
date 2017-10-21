    boolean shiftPrefixes() {

        if (token.namePrePrePrefix != null) {
            return false;
        }

        token.namePrePrePrefix        = token.namePrePrefix;
        token.isDelimitedPrePrePrefix = token.isDelimitedPrePrefix;
        token.namePrePrefix           = token.namePrefix;
        token.isDelimitedPrePrefix    = token.isDelimitedPrefix;
        token.namePrefix              = token.tokenString;
        token.isDelimitedPrefix = (token.tokenType
                                   == Tokens.X_DELIMITED_IDENTIFIER);

        return true;
    }
