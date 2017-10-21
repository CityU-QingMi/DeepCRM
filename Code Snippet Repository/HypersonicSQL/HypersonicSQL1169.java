    Token duplicate() {

        Token token = new Token();

        token.tokenString              = tokenString;
        token.tokenType                = tokenType;
        token.dataType                 = dataType;
        token.tokenValue               = tokenValue;
        token.namePrefix               = namePrefix;
        token.namePrePrefix            = namePrePrefix;
        token.namePrePrePrefix         = namePrePrePrefix;
        token.charsetSchema            = charsetSchema;
        token.charsetName              = charsetName;
        token.fullString               = fullString;
        token.lobMultiplierType        = lobMultiplierType;
        token.isDelimiter              = isDelimiter;
        token.isDelimitedIdentifier    = isDelimitedIdentifier;
        token.isDelimitedPrefix        = isDelimitedPrefix;
        token.isDelimitedPrePrefix     = isDelimitedPrePrefix;
        token.isDelimitedPrePrePrefix  = isDelimitedPrePrePrefix;
        token.isUndelimitedIdentifier  = isUndelimitedIdentifier;
        token.hasIrregularChar         = hasIrregularChar;
        token.isReservedIdentifier     = isReservedIdentifier;
        token.isCoreReservedIdentifier = isCoreReservedIdentifier;
        token.isHostParameter          = isHostParameter;
        token.isMalformed              = isMalformed;

        return token;
    }
