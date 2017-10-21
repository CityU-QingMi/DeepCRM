	@Test
	public void testFindRevisions() {
		AuditReader vr = getAuditReader();

		Set<Number> revNumbers = new HashSet<Number>();
		revNumbers.add( 1l );
		revNumbers.add( 2l );

		Map<Number, LongRevNumberRevEntity> revisionMap = vr.findRevisions( LongRevNumberRevEntity.class, revNumbers );
		assert (revisionMap.size() == 2);
		assert (revisionMap.get( 1l ).equals( vr.findRevision( LongRevNumberRevEntity.class, 1l ) ));
		assert (revisionMap.get( 2l ).equals( vr.findRevision( LongRevNumberRevEntity.class, 2l ) ));
	}
