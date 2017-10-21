    NumberSequence readSequence(ColumnSchema column) {

        readThis(Tokens.GENERATED);

        NumberSequence sequence;

        sequence = new NumberSequence(null, column.getDataType());

        boolean generatedAlways = false;

        if (token.tokenType == Tokens.BY) {
            read();
            readThis(Tokens.DEFAULT);
        } else {
            readThis(Tokens.ALWAYS);

            generatedAlways = true;
        }

        readThis(Tokens.AS);
        readThis(Tokens.IDENTITY);
        sequence.setAlways(generatedAlways);

        if (token.tokenType == Tokens.OPENBRACKET) {
            read();
            readSequenceOptions(sequence, false, false, false);
            readThis(Tokens.CLOSEBRACKET);
        }

        sequence.checkValues();

        return sequence;
    }
