	protected void validateNamedParameters(@Nullable Map<String, ?> parameters) throws InvalidDataAccessApiUsageException {
		checkCompiled();
		Map<String, ?> paramsToUse = (parameters != null ? parameters : Collections.<String, Object> emptyMap());
		int declaredInParameters = 0;
		for (SqlParameter param : this.declaredParameters) {
			if (param.isInputValueProvided()) {
				if (!supportsLobParameters() &&
						(param.getSqlType() == Types.BLOB || param.getSqlType() == Types.CLOB)) {
					throw new InvalidDataAccessApiUsageException(
							"BLOB or CLOB parameters are not allowed for this kind of operation");
				}
				if (param.getName() != null && !paramsToUse.containsKey(param.getName())) {
					throw new InvalidDataAccessApiUsageException("The parameter named '" + param.getName() +
							"' was not among the parameters supplied: " + paramsToUse.keySet());
				}
				declaredInParameters++;
			}
		}
		validateParameterCount(paramsToUse.size(), declaredInParameters);
	}
