    private static boolean isSafeChar(int c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c == '-' || c == '_' || c == '.' || c == '!' ||
                c == '~' || c == '*' || c == '\'' || c == '(' || c == ')') {
            return true;
        }
        return false;
    }
