	@Nullable
	public Object lookupLocalVariable(String name) {
		int scopeNumber = initVariableScopes().size() - 1;
		for (int i = scopeNumber; i >= 0; i--) {
			VariableScope scope = initVariableScopes().get(i);
			if (scope.definesVariable(name)) {
				return scope.lookupVariable(name);
			}
		}
		return null;
	}
