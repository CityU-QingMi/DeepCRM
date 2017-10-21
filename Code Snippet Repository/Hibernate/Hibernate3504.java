	private String getInstanceClass(
			final ResultSet rs,
			final int i,
			final Loadable persister,
			final Serializable id,
			final SharedSessionContractImplementor session) throws HibernateException, SQLException {

		if ( persister.hasSubclasses() ) {

			// Code to handle subclasses of topClass
			final Object discriminatorValue = persister.getDiscriminatorType().nullSafeGet(
					rs,
					getEntityAliases()[i].getSuffixedDiscriminatorAlias(),
					session,
					null
			);

			final String result = persister.getSubclassForDiscriminatorValue( discriminatorValue );

			if ( result == null ) {
				//woops we got an instance of another class hierarchy branch
				throw new WrongClassException(
						"Discriminator: " + discriminatorValue,
						id,
						persister.getEntityName()
				);
			}

			return result;

		}
		else {
			return persister.getEntityName();
		}
	}
