    @Before
    public void before()
    {
        _test._bufferSize=-1;
        _test._mimeType=null;
        _test._content=new byte[128];
        Arrays.fill(_test._content,(byte)'X');
        _test._content[_test._content.length-1]='\n';
        _test._writes=10;
        _test._flush=false;
        _test._close=false;
        _test._reset=false;
    }
