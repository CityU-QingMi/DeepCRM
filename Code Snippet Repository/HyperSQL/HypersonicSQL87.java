    public static Vector getDatabaseURIs() {

        Vector v = new Vector();

        synchronized (databaseIDMap) {
            Iterator it = databaseIDMap.values().iterator();

            while (it.hasNext()) {
                Database db = (Database) it.next();

                v.addElement(db.getURI());
            }
        }

        return v;
    }
