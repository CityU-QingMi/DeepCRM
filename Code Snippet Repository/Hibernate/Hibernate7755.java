	@Override
	protected void prepareBootstrapRegistryBuilder(BootstrapServiceRegistryBuilder builder) {
		super.prepareBootstrapRegistryBuilder( builder );
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
						serviceRegistry.getService( EventListenerRegistry.class ).getEventListenerGroup(
								EventType.POST_COMMIT_DELETE
						).appendListener( postCommitDeleteEventListener );
						serviceRegistry.getService( EventListenerRegistry.class ).getEventListenerGroup(
								EventType.POST_COMMIT_UPDATE
						).appendListener( postCommitUpdateEventListener );
						serviceRegistry.getService( EventListenerRegistry.class ).getEventListenerGroup(
								EventType.POST_COMMIT_INSERT
						).appendListener( postCommitInsertEventListener );
					}

					@Override
					public void disintegrate(
							SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
					}
				}
		);
	}
