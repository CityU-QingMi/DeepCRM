    final public void Value() throws ParseException {
        AstValue jjtn001 = new AstValue(JJTVALUE);
        boolean jjtc001 = true;
        jjtree.openNodeScope(jjtn001);
        try {
            ValuePrefix();
            label_9:
            while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case DOT:
                    case LBRACK:
                        ;
                        break;
                    default:
                        jj_la1[24] = jj_gen;
                        break label_9;
                }
                ValueSuffix();
            }
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
                jjtree.closeNodeScope(jjtn001, jjtree.nodeArity() > 1);
            }
        }
    }
