    Statement compileOpenCursorStatement(StatementCompound context) {

        readThis(Tokens.OPEN);
        checkIsSimpleName();

        String tokenString = token.tokenString;

        read();

        for (int i = 0; i < context.cursors.length; i++) {
            if (context.cursors[i].getCursorName().name.equals(tokenString)) {
                return context.cursors[i];
            }
        }

        throw Error.parseError(ErrorCode.X_34000, null,
                               scanner.getLineNumber());
    }
