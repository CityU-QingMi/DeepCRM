	private void addEntityListeners(final Order order) {

		EventListenerRegistry registry = sessionFactory().getServiceRegistry()
				.getService( EventListenerRegistry.class );
		registry.setListeners(
				EventType.PRE_INSERT,
				new PreInsertEventListener() {
					@Override
					public boolean onPreInsert(PreInsertEvent event) {
						if ( Order.class.isInstance( event.getEntity() ) ) {
							assertEquals( order, event.getEntity());
							assertEquals( order.items, ( (Order) event.getEntity() ).items );
						}
						return false;
					}
				}
		);

		registry.setListeners(
				EventType.POST_INSERT,
				new PostInsertEventListener() {
					public void onPostInsert(PostInsertEvent event) {
						if ( Order.class.isInstance( event.getEntity() ) ) {
							assertEquals( order, event.getEntity());
							assertEquals( order.items, ( (Order) event.getEntity() ).items );
						}
					}

					public boolean requiresPostCommitHanding(EntityPersister persister) {
						return false;
					}
				}
		);
	}
