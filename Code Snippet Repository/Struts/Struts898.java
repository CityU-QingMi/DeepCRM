    public static int indexOf( char chars[], int off, int cend, char qq )
    {
        while( off < cend ) {
            char b=chars[off];
            if( b==qq ) {
                return off;
            }
            off++;
        }
        return -1;
    }
