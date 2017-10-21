	@Override
	public void afterPropertiesSet() throws Exception {
		boolean hasContextPath = StringUtils.hasLength(this.contextPath);
		boolean hasClassesToBeBound = !ObjectUtils.isEmpty(this.classesToBeBound);
		boolean hasPackagesToScan = !ObjectUtils.isEmpty(this.packagesToScan);

		if (hasContextPath && (hasClassesToBeBound || hasPackagesToScan) ||
				(hasClassesToBeBound && hasPackagesToScan)) {
			throw new IllegalArgumentException("Specify either 'contextPath', 'classesToBeBound', " +
					"or 'packagesToScan'");
		}
		if (!hasContextPath && !hasClassesToBeBound && !hasPackagesToScan) {
			throw new IllegalArgumentException(
					"Setting either 'contextPath', 'classesToBeBound', " + "or 'packagesToScan' is required");
		}
		if (!this.lazyInit) {
			getJaxbContext();
		}
		if (!ObjectUtils.isEmpty(this.schemaResources)) {
			this.schema = loadSchema(this.schemaResources, this.schemaLanguage);
		}
	}
