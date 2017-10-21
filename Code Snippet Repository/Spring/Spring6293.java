	public void setParameters(SqlParameter... parameters) {
		if (isCompiled()) {
			throw new InvalidDataAccessApiUsageException("Cannot add parameters once the query is compiled");
		}
		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i] != null) {
				this.declaredParameters.add(parameters[i]);
			}
			else {
				throw new InvalidDataAccessApiUsageException("Cannot add parameter at index " + i + " from " +
						Arrays.asList(parameters) + " since it is 'null'");
			}
		}
	}
