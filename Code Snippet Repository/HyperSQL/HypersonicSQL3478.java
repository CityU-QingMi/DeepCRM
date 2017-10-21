    protected String getActualResultString() {

        StringBuffer printVal   = new StringBuffer();
        String[]     actualRows = getActualRows();

        if (actualRows == null) {
            return "no result";
        }

        for (int i = 0; i < actualRows.length; i++) {
            printVal.append(actualRows[i]).append(LS);
        }

        return printVal.toString();
    }
