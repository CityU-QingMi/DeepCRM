    public static boolean isValidIANAEncoding(String ianaEncoding) {
        if (ianaEncoding != null) {
            int length = ianaEncoding.length();
            if (length > 0) {
                char c = ianaEncoding.charAt(0);
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    for (int i = 1; i < length; i++) {
                        c = ianaEncoding.charAt(i);
                        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') &&
                            (c < '0' || c > '9') && c != '.' && c != '_' &&
                            c != '-') {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    } // isValidIANAEncoding(String):boolean
