    @Test
    public void testNonMatchingRequirementProperty()
    {
        ToolchainModel model = new ToolchainModel();
        model.setType( "TYPE" );
        DefaultToolchain toolchain = newDefaultToolchain( model );
        toolchain.addProvideToken( "name", RequirementMatcherFactory.createExactMatcher( "Jane Doe" ) );

        assertFalse( toolchain.matchesRequirements( Collections.singletonMap( "name", "John Doe" ) ) );
        verify( logger ).debug( "Toolchain type:TYPE{name = Jane Doe} doesn't match required property: name" );
    }
