    @Test
    public void testGetType()
    {
        ToolchainModel model = new ToolchainModel();
        DefaultToolchain toolchain = newDefaultToolchain( model, "TYPE" );
        assertEquals( "TYPE", toolchain.getType() );

        model.setType( "MODEL_TYPE" );
        toolchain = newDefaultToolchain( model );
        assertEquals( "MODEL_TYPE", toolchain.getType() );
    }
