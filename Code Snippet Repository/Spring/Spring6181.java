	public String createCallString() {
		Assert.state(this.metaDataProvider != null, "No CallMetaDataProvider available");

		String callString;
		int parameterCount = 0;
		String catalogNameToUse;
		String schemaNameToUse;

		// For Oracle where catalogs are not supported we need to reverse the schema name
		// and the catalog name since the cataog is used for the package name
		if (this.metaDataProvider.isSupportsSchemasInProcedureCalls() &&
				!this.metaDataProvider.isSupportsCatalogsInProcedureCalls()) {
			schemaNameToUse = this.metaDataProvider.catalogNameToUse(getCatalogName());
			catalogNameToUse = this.metaDataProvider.schemaNameToUse(getSchemaName());
		}
		else {
			catalogNameToUse = this.metaDataProvider.catalogNameToUse(getCatalogName());
			schemaNameToUse = this.metaDataProvider.schemaNameToUse(getSchemaName());
		}

		String procedureNameToUse = this.metaDataProvider.procedureNameToUse(getProcedureName());
		if (isFunction() || isReturnValueRequired()) {
			callString = "{? = call " +
					(StringUtils.hasLength(catalogNameToUse) ? catalogNameToUse + "." : "") +
					(StringUtils.hasLength(schemaNameToUse) ? schemaNameToUse + "." : "") +
					procedureNameToUse + "(";
			parameterCount = -1;
		}
		else {
			callString = "{call " +
					(StringUtils.hasLength(catalogNameToUse) ? catalogNameToUse + "." : "") +
					(StringUtils.hasLength(schemaNameToUse) ? schemaNameToUse + "." : "") +
					procedureNameToUse + "(";
		}

		for (SqlParameter parameter : this.callParameters) {
			if (!(parameter.isResultsParameter())) {
				if (parameterCount > 0) {
					callString += ", ";
				}
				if (parameterCount >= 0) {
					callString += createParameterBinding(parameter);
				}
				parameterCount++;
			}
		}
		callString += ")}";

		return callString;
	}
