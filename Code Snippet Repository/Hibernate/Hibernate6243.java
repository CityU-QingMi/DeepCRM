	@Test
	public void testSqlTrimFunction() {
		String fragment = "trim( col )";
		String template = Template.renderWhereStringTemplate( fragment, Template.TEMPLATE, DIALECT, FUNCTION_REGISTRY );
		assertEquals( "trim(" + Template.TEMPLATE + ".col)", template );

		fragment = "trim( from col )";
		template = Template.renderWhereStringTemplate( fragment, Template.TEMPLATE, DIALECT, FUNCTION_REGISTRY );
		assertEquals( "trim(from " + Template.TEMPLATE + ".col)", template );

		fragment = "trim( both from col )";
		template = Template.renderWhereStringTemplate( fragment, Template.TEMPLATE, DIALECT, FUNCTION_REGISTRY );
		assertEquals( "trim(both from " + Template.TEMPLATE + ".col)", template );

		fragment = "trim( leading from col )";
		template = Template.renderWhereStringTemplate( fragment, Template.TEMPLATE, DIALECT, FUNCTION_REGISTRY );
		assertEquals( "trim(leading from " + Template.TEMPLATE + ".col)", template );

		fragment = "trim( TRAILING from col )";
		template = Template.renderWhereStringTemplate( fragment, Template.TEMPLATE, DIALECT, FUNCTION_REGISTRY );
		assertEquals( "trim(TRAILING from " + Template.TEMPLATE + ".col)", template );

		fragment = "trim( 'b' from col )";
		template = Template.renderWhereStringTemplate( fragment, Template.TEMPLATE, DIALECT, FUNCTION_REGISTRY );
		assertEquals( "trim('b' from " + Template.TEMPLATE + ".col)", template );

		fragment = "trim( both 'b' from col )";
		template = Template.renderWhereStringTemplate( fragment, Template.TEMPLATE, DIALECT, FUNCTION_REGISTRY );
		assertEquals( "trim(both 'b' from " + Template.TEMPLATE + ".col)", template );
	}
