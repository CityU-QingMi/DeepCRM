    private boolean parseFunction() {
        if (!(curToken instanceof Id) || isELReserved(curToken.toString())) {
            return false;
        }
        String s1 = null; // Function prefix
        String s2 = curToken.toString(); // Function name
        int mark = getIndex();
        if (hasNext()) {
            Token t = nextToken();
            if (t.toChar() == ':') {
                if (hasNext()) {
                    Token t2 = nextToken();
                    if (t2 instanceof Id) {
                        s1 = s2;
                        s2 = t2.toString();
                        if (hasNext()) {
                            t = nextToken();
                        }
                    }
                }
            }
            if (t.toChar() == '(') {
                ELexpr.add(new ELNode.Function(s1, s2));
                return true;
            }
        }
        setIndex(mark);
        return false;
    }
