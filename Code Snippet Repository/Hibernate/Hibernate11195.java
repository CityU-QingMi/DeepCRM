	@Test
	public void testFindRevisions() {
		AuditReader vr = getAuditReader();

		Set<Number> revNumbers = new HashSet<Number>();
		revNumbers.add( 1 );
		revNumbers.add( 2 );

		Map<Number, ListenerRevEntity> revisionMap = vr.findRevisions( ListenerRevEntity.class, revNumbers );
		assert (revisionMap.size() == 2);
		assert (revisionMap.get( 1 ).equals( vr.findRevision( ListenerRevEntity.class, 1 ) ));
		assert (revisionMap.get( 2 ).equals( vr.findRevision( ListenerRevEntity.class, 2 ) ));
	}
