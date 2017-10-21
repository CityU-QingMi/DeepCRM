	public PropertyAuditExpression(
			String alias,
			PropertyNameGetter propertyNameGetter,
			String otherAlias,
			String otherPropertyName,
			String op
	) {
		this.alias = alias;
		this.propertyNameGetter = propertyNameGetter;
		this.otherAlias = otherAlias;
		this.otherPropertyName = otherPropertyName;
		this.op = op;
	}
