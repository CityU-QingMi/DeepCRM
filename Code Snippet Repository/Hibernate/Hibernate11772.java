	@Test
	public void testSupportsAllAccessTypesButTx() {
		for ( AccessType type : AccessType.values() ) {
			if ( type != AccessType.TRANSACTIONAL ) {
				assertThat(
						"JCacheTransactionalDataRegion should support " + type,
						region.supportedAccessTypes().contains( type ),
						is( true )
				);
			}
			else {
				assertThat(
						"JCacheTransactionalDataRegion NOT should support " + type,
						region.supportedAccessTypes().contains( type ),
						is( false )
				);
			}
		}
	}
