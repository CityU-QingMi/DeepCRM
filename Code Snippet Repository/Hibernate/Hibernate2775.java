	public boolean hasPhysicalDiscriminatorColumn(Queryable persister) {
		if ( persister.getDiscriminatorType() != null ) {
			String discrimColumnName = persister.getDiscriminatorColumnName();
			// Needed the "clazz_" check to work around union-subclasses
			// TODO : is there a way to tell whether a persister is truly discrim-column based inheritence?
			if ( discrimColumnName != null && !"clazz_".equals( discrimColumnName ) ) {
				return true;
			}
		}
		return false;
	}
