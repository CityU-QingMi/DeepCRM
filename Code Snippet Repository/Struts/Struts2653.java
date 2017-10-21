    final public void ValueSuffix() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case DOT:
                DotSuffix();
                break;
            case LBRACK:
                BracketSuffix();
                break;
            default:
                jj_la1[26] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }
