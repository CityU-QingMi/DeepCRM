    final public void Choice() throws ParseException {
        Or();
        label_2:
        while (true) {
            if (jj_2_1(3)) {
                ;
            } else {
                break label_2;
            }
            jj_consume_token(QUESTIONMARK);
            Choice();
            jj_consume_token(COLON);
            AstChoice jjtn001 = new AstChoice(JJTCHOICE);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            try {
                Choice();
            } catch (Throwable jjte001) {
                if (jjtc001) {
                    jjtree.clearNodeScope(jjtn001);
                    jjtc001 = false;
                } else {
                    jjtree.popNode();
                }
                if (jjte001 instanceof RuntimeException) {
                    {
                        if (true) throw (RuntimeException) jjte001;
                    }
                }
                if (jjte001 instanceof ParseException) {
                    {
                        if (true) throw (ParseException) jjte001;
                    }
                }
                {
                    if (true) throw (Error) jjte001;
                }
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 3);
                }
            }
        }
    }
