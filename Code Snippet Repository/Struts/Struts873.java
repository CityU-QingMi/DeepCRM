    public boolean equals( byte b2[], int off2, int len2) {
        byte b1[]=buff;
        if( b1==null && b2==null ) {
            return true;
        }

        int len=end-start;
        if ( len2 != len || b1==null || b2==null ) {
            return false;
        }

        int off1 = start;

        while ( len-- > 0) {
            if (b1[off1++] != b2[off2++]) {
                return false;
            }
        }
        return true;
    }
