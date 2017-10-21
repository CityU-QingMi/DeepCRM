    final public void LiteralExpression() throws ParseException {
                                               /*@bgen(jjtree) LiteralExpression */
        AstLiteralExpression jjtn000 = new AstLiteralExpression(JJTLITERALEXPRESSION);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token t = null;
        try {
            t = jj_consume_token(LITERAL_EXPRESSION);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtn000.setImage(t.image);
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
