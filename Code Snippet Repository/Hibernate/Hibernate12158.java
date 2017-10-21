	@Override
	public AnnotationMetaAttribute visitExecutable(ExecutableType t, Element p) {
		if ( !p.getKind().equals( ElementKind.METHOD ) ) {
			return null;
		}

		String string = p.getSimpleName().toString();
		if ( !StringUtil.isProperty( string, TypeUtils.toTypeString( t.getReturnType() ) ) ) {
			return null;
		}

		TypeMirror returnType = t.getReturnType();
		return returnType.accept( this, p );
	}
