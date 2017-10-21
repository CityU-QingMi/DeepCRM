	@Override
	public boolean isCompilable() {
		if (this.method == null || this.argumentConversionOccurred) {
			return false;
		}
		int methodModifiers = this.method.getModifiers();
		if (!Modifier.isStatic(methodModifiers) || !Modifier.isPublic(methodModifiers) ||
				!Modifier.isPublic(this.method.getDeclaringClass().getModifiers())) {
			return false;
		}
		for (SpelNodeImpl child : this.children) {
			if (!child.isCompilable()) {
				return false;
			}
		}
		return true;
	}
