	@Test
	@Priority(10)
	public void initData() {
		uniquePropsAudit = metadata().getEntityBinding(
				"org.hibernate.envers.test.entities.components.UniquePropsEntity_AUD"
		);
		uniquePropsNotAuditedAudit = metadata().getEntityBinding(
				"org.hibernate.envers.test.entities.components.UniquePropsNotAuditedEntity_AUD"
		);

		// Revision 1
		Session session = openSession();
		session.getTransaction().begin();
		UniquePropsEntity ent = new UniquePropsEntity();
		ent.setData1( "data1" );
		ent.setData2( "data2" );
		session.persist( ent );
		session.getTransaction().commit();

		entityRev1 = new UniquePropsEntity( ent.getId(), ent.getData1(), ent.getData2() );

		// Revision 2
		session.getTransaction().begin();
		UniquePropsNotAuditedEntity entNotAud = new UniquePropsNotAuditedEntity();
		entNotAud.setData1( "data3" );
		entNotAud.setData2( "data4" );
		session.persist( entNotAud );
		session.getTransaction().commit();

		entityNotAuditedRev2 = new UniquePropsNotAuditedEntity( entNotAud.getId(), entNotAud.getData1(), null );
	}
