	@Override
	public Size[] defaultSizes(Mapping mapping) throws MappingException {
		//Not called at runtime so doesn't matter if its slow :)
		final Size[] sizes = new Size[getColumnSpan( mapping )];
		int soFar = 0;
		for ( Type propertyType : propertyTypes ) {
			final Size[] propertySizes = propertyType.defaultSizes( mapping );
			System.arraycopy( propertySizes, 0, sizes, soFar, propertySizes.length );
			soFar += propertySizes.length;
		}
		return sizes;
	}
