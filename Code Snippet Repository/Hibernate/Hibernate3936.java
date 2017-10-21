	private void checkCompositeIdentifier() {
		if ( getIdentifier() instanceof Component ) {
			Component id = (Component) getIdentifier();
			if ( !id.isDynamic() ) {
				final Class idClass = id.getComponentClass();
				if ( idClass != null ) {
					final String idComponentClassName = idClass.getName();
					if ( !ReflectHelper.overridesEquals( idClass ) ) {
						LOG.compositeIdClassDoesNotOverrideEquals( idComponentClassName );
					}
					if ( !ReflectHelper.overridesHashCode( idClass ) ) {
						LOG.compositeIdClassDoesNotOverrideHashCode( idComponentClassName );
					}
					if ( !Serializable.class.isAssignableFrom( idClass ) ) {
						throw new MappingException(
								"Composite-id class must implement Serializable: " + idComponentClassName
						);
					}
				}
			}
		}
	}
