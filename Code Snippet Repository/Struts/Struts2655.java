    final public void BracketSuffix() throws ParseException {
                                       /*@bgen(jjtree) BracketSuffix */
        AstBracketSuffix jjtn000 = new AstBracketSuffix(JJTBRACKETSUFFIX);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        try {
            jj_consume_token(LBRACK);
            Expression();
            jj_consume_token(RBRACK);
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    if (true) throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    if (true) throw (ParseException) jjte000;
                }
            }
            {
                if (true) throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
