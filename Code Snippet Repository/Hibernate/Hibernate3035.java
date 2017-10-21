	public FilterHelper(List<FilterConfiguration> filters, SessionFactoryImplementor factory) {
		int filterCount = filters.size();
		filterNames = new String[filterCount];
		filterConditions = new String[filterCount];
		filterAutoAliasFlags = new boolean[filterCount];
		filterAliasTableMaps = new Map[filterCount];
		filterCount = 0;
		for ( final FilterConfiguration filter : filters ) {
			filterAutoAliasFlags[filterCount] = false;
			filterNames[filterCount] = filter.getName();
			filterConditions[filterCount] = filter.getCondition();
			filterAliasTableMaps[filterCount] = filter.getAliasTableMap( factory );
			if ( ( filterAliasTableMaps[filterCount].isEmpty() || isTableFromPersistentClass( filterAliasTableMaps[filterCount] ) ) && filter
					.useAutoAliasInjection() ) {
				filterConditions[filterCount] = Template.renderWhereStringTemplate(
						filter.getCondition(),
						FilterImpl.MARKER,
						factory.getDialect(),
						factory.getSqlFunctionRegistry()
				);
				filterAutoAliasFlags[filterCount] = true;
			}
			filterConditions[filterCount] = StringHelper.replace(
					filterConditions[filterCount],
					":",
					":" + filterNames[filterCount] + "."
			);
			filterCount++;
		}
	}
