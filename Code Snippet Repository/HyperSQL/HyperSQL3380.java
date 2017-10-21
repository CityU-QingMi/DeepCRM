    Table XreadTableSubqueryOrNull(boolean parens) {

        boolean joinedTable = false;
        int     position;

        position = getPosition();

        readThis(Tokens.OPENBRACKET);

        switch (token.tokenType) {

            case Tokens.TABLE :
            case Tokens.VALUES :
            case Tokens.SELECT :
            case Tokens.WITH :
                break;

            case Tokens.OPENBRACKET :
                if (parens) {
                    break;
                }

            // fall through
            default :
                joinedTable = true;
        }

        if (joinedTable) {
            rewind(position);

            return null;
        } else {
            TableDerived td = XreadSubqueryTableBody(OpTypes.TABLE_SUBQUERY);

            readThis(Tokens.CLOSEBRACKET);

            return td;
        }
    }
