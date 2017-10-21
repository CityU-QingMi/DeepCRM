	@Test
	public void testOverrideStorage() {
		String previousValue = System.setProperty( AvailableSettings.STORAGE_ENGINE, "myisam" );
		try {
			assertEquals( " engine=MyISAM", new MySQL57Dialect().getTableTypeString() );
		}
		finally {
			if ( previousValue != null ) {
				System.setProperty( AvailableSettings.STORAGE_ENGINE, previousValue );
			}
			else {
				System.clearProperty( AvailableSettings.STORAGE_ENGINE );
				assertThat( System.getProperty( AvailableSettings.STORAGE_ENGINE ), is( nullValue() ) );
			}
		}
	}
