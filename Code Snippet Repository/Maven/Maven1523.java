    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();

        reader = lookup( ModelReader.class );
        writer = lookup( ModelWriter.class );
        assembler = lookup( InheritanceAssembler.class );
    }
