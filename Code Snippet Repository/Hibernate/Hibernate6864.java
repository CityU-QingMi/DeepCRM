	@Test
	public void testOnDeleteWithoutJoinColumn() throws Exception {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.build();

		try {
			new MetadataSources( serviceRegistry )
				.addAnnotatedClass( OnDeleteUnidirectionalOneToMany.class )
				.addAnnotatedClass( ParentUnawareChild.class )
				.getMetadataBuilder()
				.build();
		}
		catch ( AnnotationException e ) {
			assertTrue(e.getMessage().contains( "Unidirectional one-to-many associations annotated with @OnDelete must define @JoinColumn" ));
		}
		finally {
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
