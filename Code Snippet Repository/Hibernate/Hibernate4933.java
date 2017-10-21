	public StandardProperty(
			String name,
			Type type,
			boolean lazy,
			boolean insertable,
			boolean updateable,
			ValueGeneration valueGenerationStrategy,
			boolean nullable,
			boolean checkable,
			boolean versionable,
			CascadeStyle cascadeStyle,
			FetchMode fetchMode) {
		super(
				null,
				null,
				-1,
				name,
				type,
				new BaselineAttributeInformation.Builder()
						.setLazy( lazy )
						.setInsertable( insertable )
						.setUpdateable( updateable )
						.setValueGenerationStrategy( valueGenerationStrategy )
						.setNullable( nullable )
						.setDirtyCheckable( checkable )
						.setVersionable( versionable )
						.setCascadeStyle( cascadeStyle )
						.setFetchMode( fetchMode )
						.createInformation()
		);
	}
