    @Before
    public void before() throws Exception
    {
        _tmp=File.createTempFile("OAPTest",null);
        if (_tmp.exists())
            IO.delete(_tmp);
        _tmp.mkdir();

        _scan = new File(_tmp,"scan").getCanonicalFile();
        _webapps = new File(_scan,OverlayedAppProvider.WEBAPPS);
        _templates = new File(_scan,OverlayedAppProvider.TEMPLATES);
        _nodes = new File(_scan,OverlayedAppProvider.NODES);
        _instances = new File(_scan,OverlayedAppProvider.INSTANCES);
        _webapps.mkdirs();
        _templates.mkdir();
        _nodes.mkdir();
        _instances.mkdir();
    }
