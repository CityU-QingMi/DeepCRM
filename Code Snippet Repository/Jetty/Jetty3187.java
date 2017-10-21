    public void initialize () throws Exception
    {
        if (!_initialized)
        {
            _initialized = true;
   
            //taking the defaults if one not set
            if (_sessionTableSchema == null)
            {
                _sessionTableSchema = new SessionTableSchema();
                addBean(_sessionTableSchema,true);
            }
            
            _dbAdaptor.initialize();
            _sessionTableSchema.setDatabaseAdaptor(_dbAdaptor);
            _sessionTableSchema.prepareTables();
        }
    }
