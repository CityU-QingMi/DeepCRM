	public static Element createSubclassEntity(
			Document document,
			String subclassType,
			AuditTableData auditTableData,
			String extendsEntityName,
			String discriminatorValue,
			Boolean isAbstract) {
		final Element classMapping = createEntityCommon(
				document,
				subclassType,
				auditTableData,
				discriminatorValue,
				isAbstract
		);

		classMapping.addAttribute( "extends", extendsEntityName );

		return classMapping;
	}
