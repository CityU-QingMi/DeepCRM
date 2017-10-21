    public static boolean isValidJavaEncoding(String javaEncoding) {
        if (javaEncoding != null) {
            int length = javaEncoding.length();
            if (length > 0) {
                for (int i = 1; i < length; i++) {
                    char c = javaEncoding.charAt(i);
                    if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') &&
                        (c < '0' || c > '9') && c != '.' && c != '_' &&
                        c != '-') {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    } // isValidIANAEncoding(String):boolean
