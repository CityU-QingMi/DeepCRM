	@Override
	protected void prepareBootstrapRegistryBuilder(BootstrapServiceRegistryBuilder builder) {
		builder.applyIntegrator(
				new Integrator() {

					@Override
					public void integrate(
							Metadata metadata,
							SessionFactoryImplementor sessionFactory,
							SessionFactoryServiceRegistry serviceRegistry) {
						integrate( serviceRegistry );
					}

					private void integrate(SessionFactoryServiceRegistry serviceRegistry) {
						EventListenerRegistry eventListenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
						eventListenerRegistry.setListeners( EventType.PERSIST, buildPersistEventListeners() );
						eventListenerRegistry.setListeners(
								EventType.PERSIST_ONFLUSH, buildPersisOnFlushEventListeners()
						);
						eventListenerRegistry.setListeners( EventType.AUTO_FLUSH, buildAutoFlushEventListeners() );
						eventListenerRegistry.setListeners( EventType.FLUSH, buildFlushEventListeners() );
						eventListenerRegistry.setListeners( EventType.FLUSH_ENTITY, buildFlushEntityEventListeners() );
					}

					@Override
					public void disintegrate(
							SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
					}
				}
		);
	}
