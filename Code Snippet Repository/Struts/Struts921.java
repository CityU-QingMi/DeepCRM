    public void toChars() {
        if( ! charC.isNull() ) {
            type=T_CHARS;
            return;
        }
        // inefficient
        toString();
        type=T_CHARS;
        char cc[]=strValue.toCharArray();
        charC.setChars(cc, 0, cc.length);
    }
