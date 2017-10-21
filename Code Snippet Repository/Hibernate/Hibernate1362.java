	private String getCondition(String cond, String name) {
		if ( BinderHelper.isEmptyAnnotationValue( cond ) ) {
			cond = buildingContext.getMetadataCollector().getFilterDefinition( name ).getDefaultFilterCondition();
			if ( StringHelper.isEmpty( cond ) ) {
				throw new AnnotationException(
						"no filter condition found for filter " + name + " in "
								+ StringHelper.qualify( propertyHolder.getPath(), propertyName )
				);
			}
		}
		return cond;
	}
