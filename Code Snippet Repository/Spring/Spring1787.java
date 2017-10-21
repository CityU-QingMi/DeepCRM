	public String getType(TypeMirror type) {
		if (type == null) {
			return null;
		}
		if (type instanceof DeclaredType) {
			DeclaredType declaredType = (DeclaredType) type;
			Element enclosingElement = declaredType.asElement().getEnclosingElement();
			if (enclosingElement != null && enclosingElement instanceof TypeElement) {
				return getQualifiedName(enclosingElement) + "$"
						+ declaredType.asElement().getSimpleName().toString();
			} else {
				return getQualifiedName(declaredType.asElement());
			}
		}
		return type.toString();
	}
