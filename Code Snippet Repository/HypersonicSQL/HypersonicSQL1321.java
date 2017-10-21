    public static DatabaseInformation newDatabaseInformation(
            Database db) {

        Class c = null;

        try {
            c = Class.forName("org.hsqldb.dbinfo.DatabaseInformationFull");
        } catch (Exception e) {
            try {
                c = Class.forName("org.hsqldb.dbinfo.DatabaseInformationMain");
            } catch (Exception e2) {
                c = DatabaseInformation.class;
            }
        }

        try {
            Class[]     ctorParmTypes = new Class[]{ Database.class };
            Object[]    ctorParms     = new Object[]{ db };
            Constructor ctor = c.getDeclaredConstructor(ctorParmTypes);

            return (DatabaseInformation) ctor.newInstance(ctorParms);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new DatabaseInformation(db);
    }
