	private boolean isProcessingId(XMLContext.Default defaults) {
		boolean isExplicit = defaults.getAccess() != null;
		boolean correctAccess =
				( PropertyType.PROPERTY.equals( propertyType ) && AccessType.PROPERTY.equals( defaults.getAccess() ) )
						|| ( PropertyType.FIELD.equals( propertyType ) && AccessType.FIELD
						.equals( defaults.getAccess() ) );
		boolean hasId = defaults.canUseJavaAnnotations()
				&& ( isPhysicalAnnotationPresent( Id.class ) || isPhysicalAnnotationPresent( EmbeddedId.class ) );
		//if ( properAccessOnMetadataComplete || properOverridingOnMetadataNonComplete ) {
		boolean mirrorAttributeIsId = defaults.canUseJavaAnnotations() &&
				( mirroredAttribute != null &&
						( mirroredAttribute.isAnnotationPresent( Id.class )
								|| mirroredAttribute.isAnnotationPresent( EmbeddedId.class ) ) );
		boolean propertyIsDefault = PropertyType.PROPERTY.equals( propertyType )
				&& !mirrorAttributeIsId;
		return correctAccess || ( !isExplicit && hasId ) || ( !isExplicit && propertyIsDefault );
	}
