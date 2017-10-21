    boolean parseExpression() throws PreprocessorException {
        boolean result = parseTerm();

        while (true) {
            switch(this.tokenizer.getTokenType()) {
                case Token.OR : {
                    this.tokenizer.next();

                    result = result | parseTerm();

                    break;
                }
                case Token.XOR : {
                    this.tokenizer.next();

                    result = result ^ parseTerm();

                    break;
                }

                default : {
                    return result;
                }
            }
        }
    }
