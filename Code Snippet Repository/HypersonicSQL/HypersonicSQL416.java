    private ExpressionLogical XreadMatchPredicateRightPart(Expression a) {

        boolean isUnique  = false;
        int     matchType = OpTypes.MATCH_SIMPLE;

        read();

        if (token.tokenType == Tokens.UNIQUE) {
            read();

            isUnique = true;
        }

        switch (token.tokenType) {

            default :
                matchType = isUnique ? OpTypes.MATCH_UNIQUE_SIMPLE
                                     : OpTypes.MATCH_SIMPLE;
                break;

            case Tokens.SIMPLE :
                read();

                matchType = isUnique ? OpTypes.MATCH_UNIQUE_SIMPLE
                                     : OpTypes.MATCH_SIMPLE;
                break;

            case Tokens.PARTIAL :
                read();

                matchType = isUnique ? OpTypes.MATCH_UNIQUE_PARTIAL
                                     : OpTypes.MATCH_PARTIAL;
                break;

            case Tokens.FULL :
                read();

                matchType = isUnique ? OpTypes.MATCH_UNIQUE_FULL
                                     : OpTypes.MATCH_FULL;
                break;
        }

        int        mode = isUnique ? OpTypes.MATCH_SIMPLE
                                   : OpTypes.IN;
        Expression s    = XreadTableSubquery(mode);

        return new ExpressionLogical(matchType, a, s);
    }
