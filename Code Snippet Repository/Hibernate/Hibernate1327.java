	public static IndexColumn buildColumnFromAnnotation(
			org.hibernate.annotations.IndexColumn ann,
			PropertyHolder propertyHolder,
			PropertyData inferredData,
			MetadataBuildingContext buildingContext) {
		final IndexColumn column;
		if ( ann != null ) {
			final String sqlType = BinderHelper.isEmptyAnnotationValue( ann.columnDefinition() ) ? null : ann.columnDefinition();
			final String name = BinderHelper.isEmptyAnnotationValue( ann.name() ) ? inferredData.getPropertyName() : ann.name();
			//TODO move it to a getter based system and remove the constructor
			column = new IndexColumn(
					false,
					sqlType,
					0,
					0,
					0,
					name,
					ann.nullable(),
					false,
					true,
					true,
					null,
					null,
					propertyHolder,
					buildingContext
			);
			column.setBase( ann.base() );
		}
		else {
			column = new IndexColumn(
					true,
					null,
					0,
					0,
					0,
					null,
					true,
					false,
					true,
					true,
					null,
					null,
					propertyHolder,
					buildingContext
			);
		}
		return column;
	}
