	@Override
	public void apply(JpaOrmXmlPersistenceUnitDefaults jpaOrmXmlPersistenceUnitDefaults) {
		if ( delegate instanceof JpaOrmXmlPersistenceUnitDefaultAware ) {
			( (JpaOrmXmlPersistenceUnitDefaultAware) delegate ).apply( jpaOrmXmlPersistenceUnitDefaults );
		}
		else {
			throw new HibernateException(
					"AbstractDelegatingMetadataBuildingOptions delegate did not " +
							"implement JpaOrmXmlPersistenceUnitDefaultAware; " +
							"cannot delegate JpaOrmXmlPersistenceUnitDefaultAware#apply"
			);
		}
	}
