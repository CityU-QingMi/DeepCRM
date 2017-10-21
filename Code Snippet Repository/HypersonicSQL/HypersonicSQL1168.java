    void reset() {

        tokenString              = "";
        tokenType                = Tokens.X_UNKNOWN_TOKEN;
        dataType                 = null;
        tokenValue               = null;
        namePrefix               = null;
        namePrePrefix            = null;
        namePrePrePrefix         = null;
        charsetSchema            = null;
        charsetName              = null;
        fullString               = null;
        lobMultiplierType        = Tokens.X_UNKNOWN_TOKEN;
        isDelimiter              = false;
        isDelimitedIdentifier    = false;
        isDelimitedPrefix        = false;
        isDelimitedPrePrefix     = false;
        isDelimitedPrePrePrefix  = false;
        isUndelimitedIdentifier  = false;
        hasIrregularChar         = false;
        isReservedIdentifier     = false;
        isCoreReservedIdentifier = false;
        isHostParameter          = false;
        isMalformed              = false;

        //
        expression    = null;
        hasColumnList = false;
    }
