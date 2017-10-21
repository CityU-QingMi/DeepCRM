    static int getExpressionType(int tokenT) {

        int type = expressionTypeMap.get(tokenT, -1);

        if (type == -1) {
            throw Error.runtimeError(ErrorCode.U_S0500, "ParserBase");
        }

        return type;
    }
