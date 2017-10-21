    static Object[] getSimplifiedTokens(Token[] tokens) {

        Object[] array = new Object[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].expression == null) {
                array[i] = tokens[i].getSQL();
            } else {
                array[i] = tokens[i].expression;
            }
        }

        return array;
    }
