    protected String getResultString() {

        StringBuffer printVal     = new StringBuffer();
        String[]     expectedRows = getExpectedRows();

        for (int i = 0; i < expectedRows.length; i++) {
            printVal.append(expectedRows[i]).append(LS);
        }

        return printVal.toString();
    }
