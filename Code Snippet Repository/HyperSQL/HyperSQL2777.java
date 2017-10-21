    protected ResultSetParsedSection(HsqlArrayList linesArray) {

        super(linesArray);

        type = 'r';

        //now we'll populate the expectedResults array
        expectedRows = new String[(resEndRow + 1)];

        for (int i = 0; i <= resEndRow; i++) {
            int skip = StringUtil.skipSpaces(lines[i], 0);

            expectedRows[i] = lines[i].substring(skip);
        }
    }
