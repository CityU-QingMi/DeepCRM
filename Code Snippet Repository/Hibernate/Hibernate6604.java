	@Test
	public void testBindingEntityWithIdAndFormula() {
		try {
			new MetadataSources( ssr )
					.addAnnotatedClass( EntityWithIdAndFormula.class )
					.buildMetadata();
			fail( "Expecting failure from invalid mapping" );
		}
		catch (CannotForceNonNullableException e) {
			assertThat( e.getMessage(), startsWith( "Identifier property [" ) );
		}
	}
