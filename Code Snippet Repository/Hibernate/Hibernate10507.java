	@Test
	public void shouldFailWhenQueryOnOneToOne() {
		//when
		try {
			getAuditReader().createQuery()
					.forEntitiesAtRevision( PlainEntity.class, 1 )
					.add( AuditEntity.relatedId( "component_oneToOneEntity" ).eq( getOneToOneEntity().getId() ) )
					.getResultList();

			//then
			fail( "This should have generated an IllegalArgumentException" );
		}
		catch ( Exception e ) {
			assertTyping( IllegalArgumentException.class, e );
		}
	}
