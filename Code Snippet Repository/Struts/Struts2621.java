    final public void Null() throws ParseException {
                     /*@bgen(jjtree) Null */
        AstNull jjtn000 = new AstNull(JJTNULL);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        try {
            jj_consume_token(NULL);
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }
