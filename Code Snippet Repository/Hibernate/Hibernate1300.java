	public static Ejb3DiscriminatorColumn buildDiscriminatorColumn(
			DiscriminatorType type, DiscriminatorColumn discAnn,
			DiscriminatorFormula discFormulaAnn,
			MetadataBuildingContext context) {
		Ejb3DiscriminatorColumn discriminatorColumn = new Ejb3DiscriminatorColumn();
		discriminatorColumn.setBuildingContext( context );
		discriminatorColumn.setImplicit( true );
		if ( discFormulaAnn != null ) {
			discriminatorColumn.setImplicit( false );
			discriminatorColumn.setFormula( discFormulaAnn.value() );
		}
		else if ( discAnn != null ) {
			discriminatorColumn.setImplicit( false );
			if ( !BinderHelper.isEmptyAnnotationValue( discAnn.columnDefinition() ) ) {
				discriminatorColumn.setSqlType(
						discAnn.columnDefinition()
				);
			}
			if ( !BinderHelper.isEmptyAnnotationValue( discAnn.name() ) ) {
				discriminatorColumn.setLogicalColumnName( discAnn.name() );
			}
			discriminatorColumn.setNullable( false );
		}
		if ( DiscriminatorType.CHAR.equals( type ) ) {
			discriminatorColumn.setDiscriminatorTypeName( "character" );
			discriminatorColumn.setImplicit( false );
		}
		else if ( DiscriminatorType.INTEGER.equals( type ) ) {
			discriminatorColumn.setDiscriminatorTypeName( "integer" );
			discriminatorColumn.setImplicit( false );
		}
		else if ( DiscriminatorType.STRING.equals( type ) || type == null ) {
			if ( discAnn != null ) discriminatorColumn.setLength( discAnn.length() );
			discriminatorColumn.setDiscriminatorTypeName( "string" );
		}
		else {
			throw new AssertionFailure( "Unknown discriminator type: " + type );
		}
		discriminatorColumn.bind();
		return discriminatorColumn;
	}
