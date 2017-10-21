	@Test
	@Priority(10)
	public void initData() {
		Session session = openSession();

		// Revision 1
		session.getTransaction().begin();
		NotAuditedDynamicMapComponent entity = new NotAuditedDynamicMapComponent( 1L, "static field value" );
		entity.getCustomFields().put( "prop1", 13 );
		entity.getCustomFields().put( "prop2", 0.1f );
		session.save( entity );
		session.getTransaction().commit();

		// No revision
		session.getTransaction().begin();
		entity = (NotAuditedDynamicMapComponent) session.get( NotAuditedDynamicMapComponent.class, entity.getId() );
		entity.getCustomFields().put( "prop1", 0 );
		session.update( entity );
		session.getTransaction().commit();

		// Revision 2
		session.getTransaction().begin();
		entity = (NotAuditedDynamicMapComponent) session.get( NotAuditedDynamicMapComponent.class, entity.getId() );
		entity.setNote( "updated note" );
		session.update( entity );
		session.getTransaction().commit();

		// Revision 3
		session.getTransaction().begin();
		entity = (NotAuditedDynamicMapComponent) session.load( NotAuditedDynamicMapComponent.class, entity.getId() );
		session.delete( entity );
		session.getTransaction().commit();

		session.close();
	}
