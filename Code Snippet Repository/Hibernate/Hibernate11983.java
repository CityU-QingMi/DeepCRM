	@Override
	public void afterCreateSchema() {
		super.afterCreateSchema();
		try {
			setGeomMetaDataTo2D();
			createIndex();
		}
		catch ( SQLException e ) {
			throw new RuntimeException( e );
		}

	}
