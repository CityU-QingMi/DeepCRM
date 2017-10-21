    public static boolean isExpression(String token, boolean isXml) {
        String openExpr;
        String closeExpr;
        if (isXml) {
            openExpr = OPEN_EXPR_XML;
            closeExpr = CLOSE_EXPR_XML;
        } else {
            openExpr = OPEN_EXPR;
            closeExpr = CLOSE_EXPR;
        }
        if (token.startsWith(openExpr) && token.endsWith(closeExpr)) {
            return true;
        } else {
            return false;
        }
    }
