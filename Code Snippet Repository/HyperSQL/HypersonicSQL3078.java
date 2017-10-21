    protected void writeSingleColumnResult(Result r) throws IOException {

        RowSetNavigator nav = r.initialiseNavigator();

        while (nav.next()) {
            Object[] data = nav.getCurrent();

            writeLogStatement(currentSession, (String) data[0]);
        }
    }
