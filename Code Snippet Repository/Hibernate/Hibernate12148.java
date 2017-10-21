		@Override
		public Boolean visitExecutable(ExecutableType t, Element element) {
			if ( !element.getKind().equals( ElementKind.METHOD ) ) {
				return Boolean.FALSE;
			}

			String string = element.getSimpleName().toString();
			if ( !StringUtil.isProperty( string, TypeUtils.toTypeString( t.getReturnType() ) ) ) {
				return Boolean.FALSE;
			}

			TypeMirror returnType = t.getReturnType();
			return returnType.accept( this, element );
		}
