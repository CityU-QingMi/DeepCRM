	protected void validateParameters(@Nullable Object[] parameters) throws InvalidDataAccessApiUsageException {
		checkCompiled();
		int declaredInParameters = 0;
		for (SqlParameter param : this.declaredParameters) {
			if (param.isInputValueProvided()) {
				if (!supportsLobParameters() &&
						(param.getSqlType() == Types.BLOB || param.getSqlType() == Types.CLOB)) {
					throw new InvalidDataAccessApiUsageException(
							"BLOB or CLOB parameters are not allowed for this kind of operation");
				}
				declaredInParameters++;
			}
		}
		validateParameterCount((parameters != null ? parameters.length : 0), declaredInParameters);
	}
