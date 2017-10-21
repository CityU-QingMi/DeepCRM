    public static Result newSingleColumnStringResult(String colName,
            String contents) {

        Result result = Result.newSingleColumnResult(colName);
        LineNumberReader lnr =
            new LineNumberReader(new StringReader(contents));

        while (true) {
            String line = null;

            try {
                line = lnr.readLine();
            } catch (Exception e) {}

            if (line == null) {
                break;
            }

            result.getNavigator().add(new Object[]{ line });
        }

        return result;
    }
