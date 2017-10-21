	@Test
	@TestForIssue( jiraKey = "" )
	public void testNonExistentColumn() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			final Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( UniqueNoNameA.class )
					.addAnnotatedClass( UniqueNoNameB.class )
					.buildMetadata();
		}
		catch (NullPointerException e) {
			fail( "The @UniqueConstraint with a non-existent column name should have resulted in an AnnotationException" );
		}
		catch (AnnotationException e) {
			// expected
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
