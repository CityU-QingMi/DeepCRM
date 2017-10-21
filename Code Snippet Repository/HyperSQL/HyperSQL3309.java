    Vector getTables(String sCatalog,
                     String[] sSchemas) throws DataAccessPointException {

        Vector AllTables = new Vector();

        if (DbStmts == null) {
            DbStmts = new Hashtable();
        }

        if (WTextRead != null) {
            try {
                WTextRead.close();

                WTextRead = null;
            } catch (IOException e) {}
        }

        this.parseFileForTables();

        StructureAlreadyParsed = false;

        Enumeration e = DbStmts.elements();

        while (e.hasMoreElements()) {
            AllTables.addElement(e.nextElement());
        }

        return AllTables;
    }
