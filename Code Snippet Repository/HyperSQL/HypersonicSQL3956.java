    static void savePrefs(String f, DataAccessPoint sourceDb,
                          DataAccessPoint targetDb, Traceable tracer,
                          Vector tTable) {

        TransferTable t;

        try {
            FileOutputStream   fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (int i = 0; i < tTable.size(); i++) {
                t          = (TransferTable) tTable.elementAt(i);
                t.sourceDb = null;
                t.destDb   = null;
                t.tracer   = null;
            }

            oos.writeObject(tTable);

            for (int i = 0; i < tTable.size(); i++) {
                t          = (TransferTable) tTable.elementAt(i);
                t.tracer   = tracer;
                t.sourceDb = (TransferDb) sourceDb;
                t.destDb   = targetDb;
            }
        } catch (IOException e) {
            System.out.println("pb in SavePrefs : " + e.toString());
            e.printStackTrace();
        }
    }
