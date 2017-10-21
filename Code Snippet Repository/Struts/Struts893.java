    public boolean equals(char b2[], int off2, int len2) {
        char b1[]=buff;
        if( b1==null && b2==null ) {
            return true;
        }

        if (b1== null || b2==null || end-start != len2) {
            return false;
        }
        int off1 = start;
        int len=end-start;
        while ( len-- > 0) {
            if (b1[off1++] != b2[off2++]) {
                return false;
            }
        }
        return true;
    }
