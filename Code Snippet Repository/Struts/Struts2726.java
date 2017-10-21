    private ELNode.Nodes parseEL() {

        StringBuffer buf = new StringBuffer();
        ELexpr = new ELNode.Nodes();
        while (hasNext()) {
            curToken = nextToken();
            if (curToken instanceof Char) {
                if (curToken.toChar() == '}') {
                    break;
                }
                buf.append(curToken.toChar());
            } else {
                // Output whatever is in buffer
                if (buf.length() > 0) {
                    ELexpr.add(new ELNode.ELText(buf.toString()));
                }
                if (!parseFunction()) {
                    ELexpr.add(new ELNode.ELText(curToken.toString()));
                }
            }
        }
        if (buf.length() > 0) {
            ELexpr.add(new ELNode.ELText(buf.toString()));
        }

        return ELexpr;
    }
