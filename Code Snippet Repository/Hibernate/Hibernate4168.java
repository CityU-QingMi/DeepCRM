	protected void initComponentPropertyPaths(
			final String path,
			final CompositeType type,
			final String[] columns,
			final String[] columnReaders,
			final String[] columnReaderTemplates,
			String[] formulaTemplates, final Mapping factory) throws MappingException {

		Type[] types = type.getSubtypes();
		String[] properties = type.getPropertyNames();
		int begin = 0;
		for ( int i = 0; i < properties.length; i++ ) {
			String subpath = extendPath( path, properties[i] );
			try {
				int length = types[i].getColumnSpan( factory );
				String[] columnSlice = ArrayHelper.slice( columns, begin, length );
				String[] columnReaderSlice = ArrayHelper.slice( columnReaders, begin, length );
				String[] columnReaderTemplateSlice = ArrayHelper.slice( columnReaderTemplates, begin, length );
				String[] formulaSlice = formulaTemplates == null ?
						null : ArrayHelper.slice( formulaTemplates, begin, length );
				initPropertyPaths(
						subpath,
						types[i],
						columnSlice,
						columnReaderSlice,
						columnReaderTemplateSlice,
						formulaSlice,
						factory
				);
				begin += length;
			}
			catch (Exception e) {
				throw new MappingException( "bug in initComponentPropertyPaths", e );
			}
		}
	}
