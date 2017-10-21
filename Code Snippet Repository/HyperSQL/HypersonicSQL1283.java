    private TokenList seekTokenSource(String nestingCommand)
            throws BadSpecial, IOException, SqlToolError {
        Token token;
        TokenList newTS = new TokenList();
        Pattern endPattern = null;
        Pattern elsePattern = null;
        if (nestingCommand != null)
            if (nestingCommand.equals("if")) {
                endPattern = Pattern.compile("end\\s+" + nestingCommand);
                elsePattern = Pattern.compile("else");
            } else if (nestingCommand.equals("else")) {
                endPattern = Pattern.compile("end\\s+if");
            } else {
                endPattern = Pattern.compile("end\\s+" + nestingCommand);
            }

        String subNestingCommand;
        Matcher inlineNestMatcher;

        while ((token = scanner.yylex()) != null) {
            if (endPattern != null && token.type == Token.PL_TYPE
                    && endPattern.matcher(token.val).matches()) return newTS;
            if (elsePattern != null && token.type == Token.PL_TYPE
                    && elsePattern.matcher(token.val).matches()) {
                assert token.nestedBlock == null:
                        "else statement's .nested block not null";
                token.nestedBlock = seekTokenSource("else");
                newTS.add(token);
                return newTS;
            }
            inlineNestMatcher = inlineNestMatcher(token);
            if (inlineNestMatcher != null) {
                processInlineBlock(token,
                        inlineNestMatcher.group(1),
                        inlineNestMatcher.group(2));
            } else {
                subNestingCommand = nestingCommand(token);
                if (subNestingCommand != null)
                    token.nestedBlock = seekTokenSource(subNestingCommand);
            }
            newTS.add(token);
        }
        if (nestingCommand == null) return newTS;
        throw new BadSpecial(
                SqltoolRB.pl_block_unterminated.getString(nestingCommand));
    }
