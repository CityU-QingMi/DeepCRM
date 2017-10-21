    public static String getExpr(String expression, boolean isXml) {
        String returnString;
        String openExpr;
        String closeExpr;
        if (isXml) {
            openExpr = OPEN_EXPR_XML;
            closeExpr = CLOSE_EXPR_XML;
        } else {
            openExpr = OPEN_EXPR;
            closeExpr = CLOSE_EXPR;
        }
        int length = expression.length();
        if (expression.startsWith(openExpr) &&
                expression.endsWith(closeExpr)) {
            returnString = expression.substring(
                    openExpr.length(), length - closeExpr.length());
        } else {
            returnString = "";
        }
        return returnString;
    }
