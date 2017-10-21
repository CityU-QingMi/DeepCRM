	public void overrideFromReferencedColumnIfNecessary(org.hibernate.mapping.Column column) {
		if (getMappingColumn() != null) {
			// columnDefinition can also be specified using @JoinColumn, hence we have to check
			// whether it is set or not
			if ( StringHelper.isEmpty( sqlType ) ) {
				sqlType = column.getSqlType();
				getMappingColumn().setSqlType( sqlType );
			}

			// these properties can only be applied on the referenced column - we can just take them over
			getMappingColumn().setLength(column.getLength());
			getMappingColumn().setPrecision(column.getPrecision());
			getMappingColumn().setScale(column.getScale());
		}
	}
