    private DefaultToolchain newDefaultToolchain( ToolchainModel model, String type )
    {
        return new DefaultToolchain( model, type, logger )
        {
            @Override
            public String findTool( String toolName )
            {
                return null;
            }
        };
    }
