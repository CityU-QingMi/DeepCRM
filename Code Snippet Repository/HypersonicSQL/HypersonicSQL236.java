    public static int toStandardIntervalPart(String format) {

        int       len = format.length();
        char      ch;
        Tokenizer tokenizer = new Tokenizer();

        for (int i = 0; i <= len; i++) {
            ch = (i == len) ? e
                            : format.charAt(i);

            if (!tokenizer.next(ch, i)) {
                int index = tokenizer.getLastMatch();

                if (index >= 0) {
                    return sqlIntervalCodes[index];
                }

                return -1;
            }
        }

        return -1;
    }
