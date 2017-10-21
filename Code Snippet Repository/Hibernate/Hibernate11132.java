	@Test
	public void testEntitiesPropertyEqualsQuery() {
		List ver1 = getAuditReader().createQuery()
				.forEntitiesAtRevision( StrIntTestEntity.class, 1 )
				.add( AuditEntity.property( "str1" ).eq( "a" ) )
				.getResultList();

		List ver2 = getAuditReader().createQuery()
				.forEntitiesAtRevision( StrIntTestEntity.class, 2 )
				.add( AuditEntity.property( "str1" ).eq( "a" ) )
				.getResultList();

		List ver3 = getAuditReader().createQuery()
				.forEntitiesAtRevision( StrIntTestEntity.class, 3 )
				.add( AuditEntity.property( "str1" ).eq( "a" ) )
				.getResultList();

		assert new HashSet( ver1 ).equals(
				TestTools.makeSet(
						new StrIntTestEntity( "a", 10, id1 ),
						new StrIntTestEntity( "a", 10, id2 )
				)
		);
		assert new HashSet( ver2 ).equals( TestTools.makeSet( new StrIntTestEntity( "a", 20, id2 ) ) );
		assert new HashSet( ver3 ).equals(
				TestTools.makeSet(
						new StrIntTestEntity( "a", 20, id2 ),
						new StrIntTestEntity( "a", 5, id3 )
				)
		);
	}
