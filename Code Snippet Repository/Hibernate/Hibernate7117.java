	@Test
	public void testStreamClosing() {
		BootstrapServiceRegistry bsr = new BootstrapServiceRegistryBuilder()
				.applyClassLoaderService( classLoaderService )
				.build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder( bsr )
				.configure( "org/hibernate/test/boot/cfgXml/hibernate.cfg.xml" )
				.build();
		try {
			for ( InputStreamWrapper openedStream : classLoaderService.openedStreams ) {
				assertTrue( openedStream.wasClosed );
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}

		assertTrue( classLoaderService.stopped );
	}
