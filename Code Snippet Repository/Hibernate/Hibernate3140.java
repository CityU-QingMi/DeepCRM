		private SimpleNaturalIdLoadAccessImpl(EntityPersister entityPersister) {
			super( entityPersister );

			if ( entityPersister.getNaturalIdentifierProperties().length != 1 ) {
				throw new HibernateException(
						String.format(
								"Entity [%s] did not define a simple natural id",
								entityPersister.getEntityName()
						)
				);
			}

			final int naturalIdAttributePosition = entityPersister.getNaturalIdentifierProperties()[0];
			this.naturalIdAttributeName = entityPersister.getPropertyNames()[naturalIdAttributePosition];
		}
