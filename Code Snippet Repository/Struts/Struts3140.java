    public static boolean isValidNCName(String ncName) {
        if (ncName.length() == 0)
            return false;
        char ch = ncName.charAt(0);
        if( isNCNameStart(ch) == false)
           return false;
        for (int i = 1; i < ncName.length(); i++ ) {
           ch = ncName.charAt(i);
           if( isNCName( ch ) == false ){
              return false;
           }
        }
        return true;
    } // isValidNCName(String):boolean
