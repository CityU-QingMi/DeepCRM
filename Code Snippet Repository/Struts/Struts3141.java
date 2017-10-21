    public static boolean isValidNmtoken(String nmtoken) {
        if (nmtoken.length() == 0)
            return false;
        for (int i = 0; i < nmtoken.length(); i++ ) {
           char ch = nmtoken.charAt(i);
           if(  ! isName( ch ) ){
              return false;
           }
        }
        return true;
    } // isValidName(String):boolean
