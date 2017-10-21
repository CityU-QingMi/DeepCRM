	private String getElementType(DeclaredType declaredType, String targetEntity) {
		if ( targetEntity != null ) {
			return targetEntity;
		}
		final List<? extends TypeMirror> mirrors = declaredType.getTypeArguments();
		if ( mirrors.size() == 1 ) {
			final TypeMirror type = mirrors.get( 0 );
			return TypeUtils.extractClosestRealTypeAsString( type, context );
		}
		else if ( mirrors.size() == 2 ) {
			return TypeUtils.extractClosestRealTypeAsString( mirrors.get( 1 ), context );
		}
		else {
			//for 0 or many
			//0 is expected, many is not
			if ( mirrors.size() > 2 ) {
				context.logMessage(
						Diagnostic.Kind.WARNING, "Unable to find the closest solid type" + declaredType
				);
			}
			return "?";
		}
	}
