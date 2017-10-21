    final public void DotSuffix() throws ParseException {
                               /*@bgen(jjtree) DotSuffix */
        AstDotSuffix jjtn000 = new AstDotSuffix(JJTDOTSUFFIX);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token t = null;
        try {
            jj_consume_token(DOT);
            t = jj_consume_token(IDENTIFIER);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtn000.setImage(t.image);
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
