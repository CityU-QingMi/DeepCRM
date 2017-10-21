	public void buildEntityManagerFactory() throws Exception {
		try {
			super.buildEntityManagerFactory();
			fail( "Should throw AnnotationException!" );
		}
		catch ( AnnotationException expected ) {
			assertTrue( expected.getMessage().startsWith(
					"@OneToMany, @ManyToMany or @ElementCollection cannot be used inside an @Embeddable that is also contained within an @ElementCollection"
			) );
		}
		finally {
			connectionProvider.stop();
		}
	}
