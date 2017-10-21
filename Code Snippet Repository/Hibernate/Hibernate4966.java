	@Override
	public void resetIdentifier(
			Object entity,
			Serializable currentId,
			Object currentVersion,
			SharedSessionContractImplementor session) {
		//noinspection StatementWithEmptyBody
		if ( entityMetamodel.getIdentifierProperty().getIdentifierGenerator() instanceof Assigned ) {
		}
		else {
			//reset the id
			Serializable result = entityMetamodel.getIdentifierProperty()
					.getUnsavedValue()
					.getDefaultValue( currentId );
			setIdentifier( entity, result, session );
			//reset the version
			VersionProperty versionProperty = entityMetamodel.getVersionProperty();
			if ( entityMetamodel.isVersioned() ) {
				setPropertyValue(
						entity,
						entityMetamodel.getVersionPropertyIndex(),
						versionProperty.getUnsavedValue().getDefaultValue( currentVersion )
				);
			}
		}
	}
