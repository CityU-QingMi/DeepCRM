	@Override
	@Nullable
	public String metaDataCatalogNameToUse(@Nullable String catalogName) {
		if (isSupportsCatalogsInProcedureCalls()) {
			return catalogNameToUse(catalogName);
		}
		else {
			return null;
		}
	}
