    private int scanEscapeDefinition() {

        int c = charAt(currentPosition);

        if (c == '\'') {
            currentPosition++;

            if (!scanWhitespace()) {
                c = charAt(currentPosition);

                if (getHexValue(c) == -1) {
                    if (c != '+' && c != '\'' && c != '\"') {
                        int escape = c;

                        currentPosition++;

                        c = charAt(currentPosition);

                        if (c == '\'') {
                            currentPosition++;

                            return escape;
                        }
                    }
                }
            }
        }

        return -1;
    }
