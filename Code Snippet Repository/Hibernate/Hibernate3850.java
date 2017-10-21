	public Comparator getComparator() {
		if ( comparator == null && comparatorClassName != null ) {
			try {
				final ClassLoaderService classLoaderService = getMetadata().getMetadataBuildingOptions()
						.getServiceRegistry()
						.getService( ClassLoaderService.class );
				setComparator( (Comparator) classLoaderService.classForName( comparatorClassName ).newInstance() );
			}
			catch (Exception e) {
				throw new MappingException(
						"Could not instantiate comparator class [" + comparatorClassName
								+ "] for collection " + getRole()
				);
			}
		}
		return comparator;
	}
