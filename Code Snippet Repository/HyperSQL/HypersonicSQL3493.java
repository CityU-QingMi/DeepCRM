    public static void testScript(Connection aConnection, String sourceName,
                                  Reader inReader)
                                  throws SQLException, IOException {

        Statement        statement = aConnection.createStatement();
        LineNumberReader reader    = new LineNumberReader(inReader);
        LineGroupReader  sqlReader = new LineGroupReader(reader);
        int              startLine = 0;

        System.out.println("Opened test script file: " + sourceName);

/**/
/**/
/**/
/**/
/**/
/**/
/**/
        try {
            while (true) {
                HsqlArrayList section = sqlReader.getSection();

                startLine = sqlReader.getStartLineNumber();

                if (section.size() == 0) {
                    break;
                }

                testSection(statement, section, sourceName, startLine);
            }

            statement.close();

            // The following catch blocks are just to report the source location
            // of the failure.
        } catch (SQLException se) {
            System.out.println("Error encountered at command beginning at "
                               + sourceName + ':' + startLine);

            throw se;
        } catch (RuntimeException re) {
            System.out.println("Error encountered at command beginning at "
                               + sourceName + ':' + startLine);

            throw re;
        }

        System.out.println("Processed " + reader.getLineNumber()
                           + " lines from " + sourceName);
    }
