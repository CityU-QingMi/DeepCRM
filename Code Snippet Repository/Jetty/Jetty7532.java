    public void setup () throws Exception
    {
       if (_useFileStore)
       {      
        _tmpdir = File.createTempFile("infini", "span");
        _tmpdir.delete();
        _tmpdir.mkdir();
        Configuration config = _builder.persistence().addSingleFileStore().location(_tmpdir.getAbsolutePath()).storeAsBinary().build();
        _manager.defineConfiguration(_name, config);
       }
       else
       {
           _manager.defineConfiguration(_name, _builder.build());
       }
       _cache = _manager.getCache(_name);
    }
