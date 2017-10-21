		@Override
		public void integrate(
				Metadata metadata,
				SessionFactoryImplementor sessionFactory,
				SessionFactoryServiceRegistry serviceRegistry) {

			// As you might expect, an EventListenerRegistry is the thing with which event
			// listeners are registered
			// It is a service so we look it up using the service registry
			final EventListenerRegistry eventListenerRegistry =
				serviceRegistry.getService( EventListenerRegistry.class );

			// If you wish to have custom determination and handling of "duplicate" listeners,
			// you would have to add an implementation of the
			// org.hibernate.event.service.spi.DuplicationStrategy contract like this
			eventListenerRegistry.addDuplicationStrategy( new CustomDuplicationStrategy() );

			// EventListenerRegistry defines 3 ways to register listeners:

			// 1) This form overrides any existing registrations with
			eventListenerRegistry.setListeners( EventType.AUTO_FLUSH,
												DefaultAutoFlushEventListener.class );

			// 2) This form adds the specified listener(s) to the beginning of the listener chain
			eventListenerRegistry.prependListeners( EventType.PERSIST,
													DefaultPersistEventListener.class );

			// 3) This form adds the specified listener(s) to the end of the listener chain
			eventListenerRegistry.appendListeners( EventType.MERGE,
												   DefaultMergeEventListener.class );
		}
