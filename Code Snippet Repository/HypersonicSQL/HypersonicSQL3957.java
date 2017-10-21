    static Vector loadPrefs(String f, DataAccessPoint sourceDb,
                            DataAccessPoint targetDb, Traceable tracer) {

        TransferTable     t;
        Vector            tTable = null;
        ObjectInputStream ois    = null;

        try {
            FileInputStream fis = new FileInputStream(f);

            ois    = new ObjectInputStream(fis);
            tTable = (Vector) ois.readObject();

            for (int i = 0; i < tTable.size(); i++) {
                t          = (TransferTable) tTable.elementAt(i);
                t.tracer   = tracer;
                t.sourceDb = (TransferDb) sourceDb;
                t.destDb   = targetDb;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("class not found pb in LoadPrefs : "
                               + e.toString());

            tTable = new Vector();
        } catch (IOException e) {
            System.out.println("IO pb in LoadPrefs : actionPerformed"
                               + e.toString());

            tTable = new Vector();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ioe) {}
            }
        }

        return (tTable);
    }
