    TransferResultSet getData(String statement)
    throws DataAccessPointException {

        StringTokenizer Tokenizer;
        String          tableName = "";

        try {
            Tokenizer = new StringTokenizer(statement);

            while (!Tokenizer.nextToken().toUpperCase().equals("FROM")) {
                ;
            }

            tableName = Tokenizer.nextToken(" ;");
        } catch (NoSuchElementException NSE) {
            throw new DataAccessPointException(
                "Table name not found in statement: " + statement);
        }

        if (WTextRead != null) {
            try {
                WTextRead.close();

                WTextRead = null;
            } catch (IOException e) {}
        }

        return (this.parseFileForData(tableName));
    }
