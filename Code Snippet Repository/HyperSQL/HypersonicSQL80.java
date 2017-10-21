    public void createObjectStructures() {

        nameManager = new HsqlNameManager(this);
        databaseUniqueName = nameManager.newHsqlName("", false,
                SchemaObject.DATABASE);
        lobManager     = new LobManager(this);
        granteeManager = new GranteeManager(this);
        userManager    = new UserManager(this);
        schemaManager  = new SchemaManager(this);
        persistentStoreCollection =
            new PersistentStoreCollectionDatabase(this);
        isReferentialIntegrity = true;
        sessionManager         = new SessionManager(this);
        collation              = Collation.newDatabaseInstance();
        dbInfo = DatabaseInformation.newDatabaseInformation(this);
        txManager              = new TransactionManager2PL(this);

        lobManager.createSchema();
        sessionManager.getSysLobSession().setSchema(SqlInvariants.LOBS_SCHEMA);
        schemaManager.setSchemaChangeTimestamp();
        schemaManager.createSystemTables();
    }
