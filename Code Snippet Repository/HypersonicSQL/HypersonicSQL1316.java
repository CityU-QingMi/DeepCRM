    public String reconstitute(boolean semify) {
        if (val == null) return "";
        switch (type) {
            case Token.SPECIAL_TYPE:
            case Token.PL_TYPE:
                return Character.toString(getTypeChar()) + val;
            case Token.SQL_TYPE:
                return val + (semify ? ";" : "");
        }
        return "? " + val;
    }
