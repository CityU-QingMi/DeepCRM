    @Override
    protected void doStart() throws Exception
    {
        if (!_dsProvided)
        {
            if (!StringUtil.isBlank(getNamespace()))
                _datastore = DatastoreOptions.newBuilder().setNamespace(getNamespace()).build().getService();
            else
                _datastore = DatastoreOptions.getDefaultInstance().getService();
        }

        if (_model == null)
        {
            _model = new EntityDataModel();
            addBean(_model,true);
        }

        _keyFactory = _datastore.newKeyFactory().setKind(_model.getKind());   
        
        _indexesPresent = checkIndexes();
        if (!_indexesPresent)
            LOG.warn("Session indexes not uploaded, falling back to less efficient queries");
        
        super.doStart();
    }
