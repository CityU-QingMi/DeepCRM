    public boolean evaluate(String expression) throws PreprocessorException {
        Tokenizer tokenizer = new Tokenizer(expression);

        tokenizer.next();

        Parser parser = new Parser(this, tokenizer);

        boolean result = parser.parseExpression();

        if (!tokenizer.isToken(Token.EOI)) {
            throw new PreprocessorException("Illegal trailing "
                    + "characters at position: "
                    + tokenizer.getStartIndex()
                    + " in ["
                    + expression
                    + "]"); // NOI18N
        }

        return result;
    }
