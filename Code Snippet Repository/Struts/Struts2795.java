    Mark skipUntilIgnoreEsc(String limit) throws JasperException {
        Mark ret = null;
        int limlen = limit.length();
        int ch;
        int prev = 'x';        // Doesn't matter
        
    skip:
        for (ret = mark(), ch = nextChar() ; ch != -1 ;
                 ret = mark(), prev = ch, ch = nextChar()) {            
            if (ch == '\\' && prev == '\\') {
                ch = 0;                // Double \ is not an escape char anymore
            }
            else if (ch == limit.charAt(0) && prev != '\\') {
                for (int i = 1 ; i < limlen ; i++) {
                    if (peekChar() == limit.charAt(i))
                        nextChar();
                    else
                        continue skip;
                }
                return ret;
            }
        }
        return null;
    }
