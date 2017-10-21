    public void append(String s, int off, int len) throws IOException {
        if (s==null) {
            return;
        }

        // will grow, up to limit
        makeSpace( len );

        // if we don't have limit: makeSpace can grow as it wants
        if( limit < 0 ) {
            // assert: makeSpace made enough space
            s.getChars(off, off+len, buff, end );
            end+=len;
            return;
        }

        int sOff = off;
        int sEnd = off + len;
        while (sOff < sEnd) {
            int d = min(limit - end, sEnd - sOff);
            s.getChars( sOff, sOff+d, buff, end);
            sOff += d;
            end += d;
            if (end >= limit) {
                flushBuffer();
            }
        }
    }
