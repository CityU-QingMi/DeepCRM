    public static boolean isValidName(String name) {
        if (name.length() == 0)
            return false;
        char ch = name.charAt(0);
        if( isNameStart(ch) == false)
           return false;
        for (int i = 1; i < name.length(); i++ ) {
           ch = name.charAt(i);
           if( isName( ch ) == false ){
              return false;
           }
        }
        return true;
    } // isValidName(String):boolean
