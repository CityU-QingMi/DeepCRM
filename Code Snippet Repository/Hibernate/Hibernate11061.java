	@Test
	public void testProxyIdentifier() {
		TargetNotAuditedEntity rev1 = getAuditReader().find( TargetNotAuditedEntity.class, tnae1.getId(), 1 );

		Assert.assertTrue( rev1.getReference() instanceof HibernateProxy );

		HibernateProxy proxyCreateByEnvers = (HibernateProxy) rev1.getReference();
		LazyInitializer lazyInitializer = proxyCreateByEnvers.getHibernateLazyInitializer();

		Assert.assertTrue( lazyInitializer.isUninitialized() );
		Assert.assertNotNull( lazyInitializer.getIdentifier() );
		Assert.assertEquals( tnae1.getId(), lazyInitializer.getIdentifier() );
		Assert.assertTrue( lazyInitializer.isUninitialized() );

		Assert.assertEquals( uste1.getId(), rev1.getReference().getId() );
		Assert.assertEquals( uste1.getStr(), rev1.getReference().getStr() );
		Assert.assertFalse( lazyInitializer.isUninitialized() );
	}
