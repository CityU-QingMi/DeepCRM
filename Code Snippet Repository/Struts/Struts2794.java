    Mark skipUntil(String limit) throws JasperException {
        Mark ret = null;
        int limlen = limit.length();
        int ch;

    skip:
        for (ret = mark(), ch = nextChar() ; ch != -1 ;
                 ret = mark(), ch = nextChar()) {
            if (ch == limit.charAt(0)) {
                Mark restart = mark();
                for (int i = 1 ; i < limlen ; i++) {
                    if (peekChar() == limit.charAt(i))
                        nextChar();
                    else {
                        reset(restart);
                        continue skip;
                    }
                }
                return ret;
            }
        }
        return null;
    }
