    final public void ValuePrefix() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case INTEGER_LITERAL:
            case FLOATING_POINT_LITERAL:
            case STRING_LITERAL:
            case TRUE:
            case FALSE:
            case NULL:
                Literal();
                break;
            case LPAREN:
            case IDENTIFIER:
                NonLiteral();
                break;
            default:
                jj_la1[25] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }
