    public TableBase(Session session, Database database, int scope, int type,
                     Type[] colTypes) {

        tableType            = type;
        persistenceScope     = scope;
        isSessionBased       = true;
        persistenceId        = database.persistentStoreCollection.getNextId();
        this.database        = database;
        this.colTypes        = colTypes;
        columnCount          = colTypes.length;
        indexList            = Index.emptyArray;
        emptyColumnCheckList = new boolean[columnCount];

        createPrimaryIndex(ValuePool.emptyIntArray, Type.emptyArray, null);
    }
