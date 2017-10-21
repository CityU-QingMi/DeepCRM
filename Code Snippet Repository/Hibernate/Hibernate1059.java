	public String buildInLineDirtyCheckingBodyFragment(JavassistEnhancementContext context, CtField currentValue) {
		StringBuilder builder = new StringBuilder();
		try {
			// should ignore primary keys
			if ( PersistentAttributesHelper.hasAnnotation( currentValue, Id.class )
					|| PersistentAttributesHelper.hasAnnotation( currentValue, EmbeddedId.class ) ) {
				return "";
			}

			String readFragment = inheritanceMetadata.isInherited() && !inheritanceMetadata.isVisible()
					? "super." + inheritanceMetadata.getReaderName() + "()"
					: "this." + currentValue.getName();

			if ( currentValue.getType().isPrimitive() || currentValue.getType().isEnum() ) {
				// primitives || enums
				builder.append( String.format( "  if ( %s != $1 )", readFragment ) );
			}
			else {
				// if the field is a collection we return since we handle that in a separate method
				for ( CtClass ctClass : currentValue.getType().getInterfaces() ) {
					if ( ctClass.getName().equals( Collection.class.getName() ) ) {
						// if the collection is not managed we should write it to the tracker
						if ( context.isMappedCollection( currentValue ) ) {
							return "";
						}
					}
				}
				builder.append(
						String.format(
								"  if ( !%s.areEqual( %s, $1 ) )",
								EqualsHelper.class.getName(),
								readFragment
						)
				);
			}
			builder.append( String.format( "  {  %s(\"%s\");  }", EnhancerConstants.TRACKER_CHANGER_NAME, currentValue.getName() ) );
		}
		catch (NotFoundException ignore) {
		}
		return builder.toString();
	}
