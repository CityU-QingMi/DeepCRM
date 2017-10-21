	@Override
	public void generateSchema() {
		// This seems overkill, but building the SF is necessary to get the Integrators to kick in.
		// Metamodel will clean this up...
		try {
			SessionFactoryBuilder sfBuilder = metadata().getSessionFactoryBuilder();
			populate( sfBuilder, standardServiceRegistry );

			SchemaManagementToolCoordinator.process(
					metadata, standardServiceRegistry, configurationValues, DelayedDropRegistryNotAvailableImpl.INSTANCE
			);
		}
		catch (Exception e) {
			throw persistenceException( "Error performing schema management", e );
		}

		// release this builder
		cancel();
	}
