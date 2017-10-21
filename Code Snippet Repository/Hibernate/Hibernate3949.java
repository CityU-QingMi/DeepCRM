	public void validate(Mapping mapping) throws MappingException {
		if ( getDiscriminator() == null ) {
			throw new MappingException(
					"No discriminator found for " + getEntityName()
							+ ". Discriminator is needed when 'single-table-per-hierarchy' "
							+ "is used and a class has subclasses"
			);
		}
		super.validate( mapping );
	}
