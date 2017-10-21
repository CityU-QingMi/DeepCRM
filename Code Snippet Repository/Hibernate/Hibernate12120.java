	@Test
	public void testRecording() {
		TestListener listener = new TestListener();
		LogInspectionHelper.registerListener( listener, LOG );

		LOG.debug( "Hey coffee is ready!" );
		assertThat( listener.isCAlled, is( true ) );
		assertThat( listener.level, is( Level.DEBUG ) );
		assertThat( (String) listener.renderedMessage, is( "Hey coffee is ready!" ) );
		assertThat( listener.thrown, nullValue() );
		LogInspectionHelper.clearAllListeners( LOG );
	}
