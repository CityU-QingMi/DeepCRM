	protected String getCatalog(String catalogFromAnnotation, Table table) {
		// Get the catalog from the annotation ...
		String catalog = catalogFromAnnotation;
		// ... if empty, try using the default ...
		if ( StringTools.isEmpty( catalog ) ) {
			catalog = globalCfg.getDefaultCatalogName();

			// ... if still empty, use the same as the normal table.
			if ( StringTools.isEmpty( catalog ) ) {
				catalog = table.getCatalog();
			}
		}

		return catalog;
	}
