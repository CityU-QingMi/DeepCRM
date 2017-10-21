    private DefaultToolchain newDefaultToolchain( ToolchainModel model )
    {
        return new DefaultToolchain( model, logger )
        {
            @Override
            public String findTool( String toolName )
            {
                return null;
            }
        };
    }
