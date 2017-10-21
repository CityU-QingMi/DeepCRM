    @Override
    protected void doStart() throws Exception
    {
        if (_out==null)
            _out=new RolloverFileOutputStream("./logs/yyyy_mm_dd.debug.log",true);
        _print=new PrintStream(_out);
        
        for (Connector connector : getServer().getConnectors())
            if (connector instanceof AbstractConnector)
                ((AbstractConnector)connector).addBean(this,false);
            
        super.doStart();
    }
