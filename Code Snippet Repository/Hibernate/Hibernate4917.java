	public BaselineAttributeInformation(
			boolean lazy,
			boolean insertable,
			boolean updateable,
			ValueGeneration valueGenerationStrategy,
			boolean nullable,
			boolean dirtyCheckable,
			boolean versionable,
			CascadeStyle cascadeStyle,
			FetchMode fetchMode) {
		this.lazy = lazy;
		this.insertable = insertable;
		this.updateable = updateable;
		this.valueGenerationStrategy = valueGenerationStrategy;
		this.nullable = nullable;
		this.dirtyCheckable = dirtyCheckable;
		this.versionable = versionable;
		this.cascadeStyle = cascadeStyle;
		this.fetchMode = fetchMode;
	}
