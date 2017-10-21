    boolean parseTerm() throws PreprocessorException {
        boolean result = parseFactor();

        while (this.tokenizer.isToken(Token.AND)) {
            this.tokenizer.next();

            result = result & parseFactor();
        }

        return result;
    }
