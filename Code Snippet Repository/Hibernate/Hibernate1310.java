	public static Ejb3JoinColumn[] buildJoinColumnsOrFormulas(
			JoinColumnOrFormula[] anns,
			String mappedBy,
			Map<String, Join> joins,
			PropertyHolder propertyHolder,
			String propertyName,
			MetadataBuildingContext buildingContext) {
		Ejb3JoinColumn [] joinColumns = new Ejb3JoinColumn[anns.length];
		for (int i = 0; i < anns.length; i++) {
			JoinColumnOrFormula join = anns[i];
			JoinFormula formula = join.formula();
			if (formula.value() != null && !formula.value().equals("")) {
				joinColumns[i] = buildJoinFormula(
						formula, mappedBy, joins, propertyHolder, propertyName, buildingContext
				);
			}
			else {
				joinColumns[i] = buildJoinColumns(
						new JoinColumn[] { join.column() }, mappedBy, joins, propertyHolder, propertyName, buildingContext
				)[0];
			}
		}

		return joinColumns;
	}
