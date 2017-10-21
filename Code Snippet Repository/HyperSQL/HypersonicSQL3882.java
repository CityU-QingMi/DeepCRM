    protected int[] getRowCounts(Vector inTable,
                                 Vector inSchema) throws Exception {

        if (!displayRowCounts) {
            return (null);
        }

        String rowCountSelect = "SELECT COUNT(*) FROM ";
        int[]  counts;
        String name;

        counts = new int[inTable.size()];

        try {
            Statement select = rowConn.createStatement();

            for (int i = 0; i < inTable.size(); i++) {
                try {
                    String schemaPart = (String) inSchema.elementAt(i);

                    schemaPart = schemaPart == null ? ""
                                                    : ("\"" + schemaPart
                                                       + "\".\"");
                    name = schemaPart + (String) inTable.elementAt(i) + "\"";

                    ResultSet resultSet = select.executeQuery(rowCountSelect
                        + name);

                    while (resultSet.next()) {
                        counts[i] = resultSet.getInt(1);
                    }
                } catch (Exception e) {
                    System.err.println("Unable to get row count for table "
                                       + inSchema.elementAt(i) + '.'
                                       + inTable.elementAt(i)
                                       + ".  Using value '0': " + e);
                }
            }
        } catch (Exception e) {
            CommonSwing.errorMessage(e);
        }

        return (counts);
    }
