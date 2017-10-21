	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		if ( oldPersistCascadeStyle == null ) {
			CascadeStyles.registerCascadeStyle(
					"persist",
					null
			);
		}
		CascadeStyles.registerCascadeStyle(
				"persist",
				(CascadeStyles.BaseCascadeStyle) oldPersistCascadeStyle
		);

		if ( callbackRegistry != null ) {
			callbackRegistry.release();
		}
		if ( callbackBuilder != null ) {
			callbackBuilder.release();
		}
		if ( jpaListenerFactory != null ) {
			jpaListenerFactory.release();
		}
	}
