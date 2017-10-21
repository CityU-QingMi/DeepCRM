    final public void String() throws ParseException {
                         /*@bgen(jjtree) String */
        AstString jjtn000 = new AstString(JJTSTRING);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token t = null;
        try {
            t = jj_consume_token(STRING_LITERAL);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtn000.setImage(t.image);
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
