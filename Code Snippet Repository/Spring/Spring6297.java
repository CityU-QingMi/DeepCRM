	private void validateParameterCount(int suppliedParamCount, int declaredInParamCount) {
		if (suppliedParamCount < declaredInParamCount) {
			throw new InvalidDataAccessApiUsageException(suppliedParamCount + " parameters were supplied, but " +
					declaredInParamCount + " in parameters were declared in class [" + getClass().getName() + "]");
		}
		if (suppliedParamCount > this.declaredParameters.size() && !allowsUnusedParameters()) {
			throw new InvalidDataAccessApiUsageException(suppliedParamCount + " parameters were supplied, but " +
					declaredInParamCount + " parameters were declared in class [" + getClass().getName() + "]");
		}
	}
