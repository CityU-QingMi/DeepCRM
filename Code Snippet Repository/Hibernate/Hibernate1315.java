	public void setJoinAnnotation(JoinColumn annJoin, String defaultName) {
		if ( annJoin == null ) {
			setImplicit( true );
		}
		else {
			setImplicit( false );
			if ( !BinderHelper.isEmptyAnnotationValue( annJoin.columnDefinition() ) ) {
				setSqlType( getBuildingContext().getObjectNameNormalizer().applyGlobalQuoting( annJoin.columnDefinition() ) );
			}
			if ( !BinderHelper.isEmptyAnnotationValue( annJoin.name() ) ) {
				setLogicalColumnName( annJoin.name() );
			}
			setNullable( annJoin.nullable() );
			setUnique( annJoin.unique() );
			setInsertable( annJoin.insertable() );
			setUpdatable( annJoin.updatable() );
			setReferencedColumn( annJoin.referencedColumnName() );

			if ( BinderHelper.isEmptyAnnotationValue( annJoin.table() ) ) {
				setExplicitTableName( "" );
			}
			else {
				final Identifier logicalIdentifier = getBuildingContext().getMetadataCollector()
						.getDatabase()
						.toIdentifier( annJoin.table() );
				final Identifier physicalIdentifier = getBuildingContext().getBuildingOptions()
						.getPhysicalNamingStrategy()
						.toPhysicalTableName( logicalIdentifier, getBuildingContext().getMetadataCollector().getDatabase().getJdbcEnvironment() );
				setExplicitTableName(
						physicalIdentifier.render( getBuildingContext().getMetadataCollector().getDatabase().getDialect() )
				);
			}
		}
	}
