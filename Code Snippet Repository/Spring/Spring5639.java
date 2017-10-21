	@Override
	public boolean isCompilable() {
		if (!(this.cachedExecutor instanceof ReflectiveConstructorExecutor) || 
			this.exitTypeDescriptor == null) {
			return false;
		}

		if (getChildCount() > 1) {
			for (int c = 1, max = getChildCount();c < max; c++) {
				if (!this.children[c].isCompilable()) {
					return false;
				}
			}
		}

		ReflectiveConstructorExecutor executor = (ReflectiveConstructorExecutor) this.cachedExecutor;
		if (executor == null) {
			return false;
		}
		Constructor<?> constructor = executor.getConstructor();
		return (Modifier.isPublic(constructor.getModifiers()) &&
				Modifier.isPublic(constructor.getDeclaringClass().getModifiers()));
	}
