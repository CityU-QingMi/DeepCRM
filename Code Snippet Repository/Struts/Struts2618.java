    final public void FloatingPoint() throws ParseException {
                                       /*@bgen(jjtree) FloatingPoint */
        AstFloatingPoint jjtn000 = new AstFloatingPoint(JJTFLOATINGPOINT);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token t = null;
        try {
            t = jj_consume_token(FLOATING_POINT_LITERAL);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtn000.setImage(t.image);
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
