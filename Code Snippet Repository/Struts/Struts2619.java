    final public void Integer() throws ParseException {
                           /*@bgen(jjtree) Integer */
        AstInteger jjtn000 = new AstInteger(JJTINTEGER);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token t = null;
        try {
            t = jj_consume_token(INTEGER_LITERAL);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtn000.setImage(t.image);
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
