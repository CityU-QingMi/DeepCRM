	@Test
	public void testErrorInstantiatingConverterClass() {
		Configuration cfg = new Configuration();
		try {
			cfg.addAttributeConverter( BlowsUpConverter.class );
			fail( "expecting an exception" );
		}
		catch (AnnotationException e) {
			assertNotNull( e.getCause() );
			assertTyping( BlewUpException.class, e.getCause() );
		}
	}
