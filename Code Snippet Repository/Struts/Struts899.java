    public int indexOf(String src, int srcOff, int srcLen, int myOff ) {
        char first=src.charAt( srcOff );

        // Look for first char
        int srcEnd = srcOff + srcLen;

        for( int i=myOff+start; i <= (end - srcLen); i++ ) {
            if( buff[i] != first ) {
                continue;
            }
            // found first char, now look for a match
            int myPos=i+1;
            for( int srcPos=srcOff + 1; srcPos< srcEnd;) {
                if( buff[myPos++] != src.charAt( srcPos++ )) {
                    break;
                }
                if( srcPos==srcEnd )
                 {
                    return i-start; // found it
                }
            }
        }
        return -1;
    }
