	@Override
	public boolean matches(Method method) {
		if (!method.getName().equals(getMethodName())) {
			return false;
		}
		if (!isOverloaded()) {
			// Not overloaded: don't worry about arg type matching...
			return true;
		}
		// If we get here, we need to insist on precise argument matching...
		if (this.typeIdentifiers.size() != method.getParameterCount()) {
			return false;
		}
		for (int i = 0; i < this.typeIdentifiers.size(); i++) {
			String identifier = this.typeIdentifiers.get(i);
			if (!method.getParameterTypes()[i].getName().contains(identifier)) {
				return false;
			}
		}
		return true;
	}
