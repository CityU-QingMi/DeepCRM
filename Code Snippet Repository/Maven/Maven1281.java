    @Test
    public void testMissingRequirementProperty()
    {
        ToolchainModel model = new ToolchainModel();
        model.setType( "TYPE" );
        DefaultToolchain toolchain = newDefaultToolchain( model );

        assertFalse( toolchain.matchesRequirements( Collections.singletonMap( "name", "John Doe" ) ) );
        verify( logger ).debug( "Toolchain type:TYPE{} is missing required property: name" );
    }
