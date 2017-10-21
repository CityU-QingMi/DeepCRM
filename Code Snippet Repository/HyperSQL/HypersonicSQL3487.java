    protected ExceptionParsedSection(HsqlArrayList linesArray) {

        super(linesArray);

        expectedState = lines[0].trim();

        if (expectedState.length() < 1) {
            expectedState = null;
        }

        type = 'e';
    }
