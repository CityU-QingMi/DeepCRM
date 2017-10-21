	@SuppressWarnings("")
	public EntityManagerFactory build() {
		SessionFactoryBuilder sfBuilder = metadata().getSessionFactoryBuilder();
		populate( sfBuilder, standardServiceRegistry );

		try {
			return sfBuilder.build();
		}
		catch (Exception e) {
			throw persistenceException( "Unable to build Hibernate SessionFactory", e );
		}
	}
