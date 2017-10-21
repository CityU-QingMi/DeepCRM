    final public void Literal() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case TRUE:
            case FALSE:
                Boolean();
                break;
            case FLOATING_POINT_LITERAL:
                FloatingPoint();
                break;
            case INTEGER_LITERAL:
                Integer();
                break;
            case STRING_LITERAL:
                String();
                break;
            case NULL:
                Null();
                break;
            default:
                jj_la1[31] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }
