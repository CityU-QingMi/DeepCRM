    final public void Boolean() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case TRUE:
                AstTrue jjtn001 = new AstTrue(JJTTRUE);
                boolean jjtc001 = true;
                jjtree.openNodeScope(jjtn001);
                try {
                    jj_consume_token(TRUE);
                } finally {
                    if (jjtc001) {
                        jjtree.closeNodeScope(jjtn001, true);
                    }
                }
                break;
            case FALSE:
                AstFalse jjtn002 = new AstFalse(JJTFALSE);
                boolean jjtc002 = true;
                jjtree.openNodeScope(jjtn002);
                try {
                    jj_consume_token(FALSE);
                } finally {
                    if (jjtc002) {
                        jjtree.closeNodeScope(jjtn002, true);
                    }
                }
                break;
            default:
                jj_la1[32] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }
