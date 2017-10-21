	@Test
	public void testHistoryOfTnae1_id() {
		// load original "tnae1" TargetNotAuditedEntity to force load "str1" UnversionedStrTestEntity as Proxy
		TargetNotAuditedEntity original = getEntityManager().find( TargetNotAuditedEntity.class, tnae1_id );

		UnversionedStrTestEntity uste1 = getEntityManager().find( UnversionedStrTestEntity.class, uste1_id );
		UnversionedStrTestEntity uste2 = getEntityManager().find( UnversionedStrTestEntity.class, uste2_id );

		TargetNotAuditedEntity rev1 = getAuditReader().find( TargetNotAuditedEntity.class, tnae1_id, 1 );
		TargetNotAuditedEntity rev2 = getAuditReader().find( TargetNotAuditedEntity.class, tnae1_id, 2 );
		TargetNotAuditedEntity rev3 = getAuditReader().find( TargetNotAuditedEntity.class, tnae1_id, 3 );
		TargetNotAuditedEntity rev4 = getAuditReader().find( TargetNotAuditedEntity.class, tnae1_id, 4 );

		assert rev1.getReference().equals( uste1 );
		assert rev2.getReference().equals( uste2 );
		assert rev3.getReference().equals( uste2 );
		assert rev4.getReference().equals( uste1 );

		assert original.getReference() instanceof HibernateProxy;
		assert UnversionedStrTestEntity.class.equals( Hibernate.getClass( original.getReference() ) );
		assert UnversionedStrTestEntity.class.equals( HibernateProxyHelper.getClassWithoutInitializingProxy( rev1.getReference() ) );
		assert UnversionedStrTestEntity.class.equals( Hibernate.getClass( rev1.getReference() ) );
	}
