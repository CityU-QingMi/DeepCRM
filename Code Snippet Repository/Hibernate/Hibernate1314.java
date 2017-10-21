	private static Ejb3JoinColumn buildJoinColumn(
			JoinColumn ann,
			String mappedBy, Map<String, Join> joins,
			PropertyHolder propertyHolder,
			String propertyName,
			String suffixForDefaultColumnName,
			MetadataBuildingContext buildingContext) {
		if ( ann != null ) {
			if ( BinderHelper.isEmptyAnnotationValue( mappedBy ) ) {
				throw new AnnotationException(
						"Illegal attempt to define a @JoinColumn with a mappedBy association: "
								+ BinderHelper.getRelativePath( propertyHolder, propertyName )
				);
			}
			Ejb3JoinColumn joinColumn = new Ejb3JoinColumn();
			joinColumn.setBuildingContext( buildingContext );
			joinColumn.setJoinAnnotation( ann, null );
			if ( StringHelper.isEmpty( joinColumn.getLogicalColumnName() )
				&& ! StringHelper.isEmpty( suffixForDefaultColumnName ) ) {
				joinColumn.setLogicalColumnName( propertyName + suffixForDefaultColumnName );
			}
			joinColumn.setJoins( joins );
			joinColumn.setPropertyHolder( propertyHolder );
			joinColumn.setPropertyName( BinderHelper.getRelativePath( propertyHolder, propertyName ) );
			joinColumn.setImplicit( false );
			joinColumn.bind();
			return joinColumn;
		}
		else {
			Ejb3JoinColumn joinColumn = new Ejb3JoinColumn();
			joinColumn.setMappedBy( mappedBy );
			joinColumn.setJoins( joins );
			joinColumn.setPropertyHolder( propertyHolder );
			joinColumn.setPropertyName(
					BinderHelper.getRelativePath( propertyHolder, propertyName )
			);
			// property name + suffix is an "explicit" column name
			if ( !StringHelper.isEmpty( suffixForDefaultColumnName ) ) {
				joinColumn.setLogicalColumnName( propertyName + suffixForDefaultColumnName );
				joinColumn.setImplicit( false );
			}
			else {
				joinColumn.setImplicit( true );
			}
			joinColumn.setBuildingContext( buildingContext );
			joinColumn.bind();
			return joinColumn;
		}
	}
