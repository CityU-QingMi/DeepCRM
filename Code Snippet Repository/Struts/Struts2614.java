    final public void NonLiteral() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case LPAREN:
                jj_consume_token(LPAREN);
                Expression();
                jj_consume_token(RPAREN);
                break;
            default:
                jj_la1[27] = jj_gen;
                if (jj_2_2(2147483647)) {
                    Function();
                } else {
                    switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                        case IDENTIFIER:
                            Identifier();
                            break;
                        default:
                            jj_la1[28] = jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                    }
                }
        }
    }
