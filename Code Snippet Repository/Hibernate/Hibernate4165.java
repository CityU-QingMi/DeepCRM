	protected void addPropertyPath(
			String path,
			Type type,
			String[] columns,
			String[] columnReaders,
			String[] columnReaderTemplates,
			String[] formulaTemplates) {
		// TODO : not quite sure yet of the difference, but this is only needed from annotations for @Id @ManyToOne support
		if ( typesByPropertyPath.containsKey( path ) ) {
			if ( LOG.isTraceEnabled() ) {
				LOG.tracev(
						"Skipping duplicate registration of path [{0}], existing type = [{1}], incoming type = [{2}]",
						path,
						typesByPropertyPath.get( path ),
						type
				);
			}
			return;
		}
		typesByPropertyPath.put( path, type );
		columnsByPropertyPath.put( path, columns );
		columnReadersByPropertyPath.put( path, columnReaders );
		columnReaderTemplatesByPropertyPath.put( path, columnReaderTemplates );
		if ( formulaTemplates != null ) {
			formulaTemplatesByPropertyPath.put( path, formulaTemplates );
		}
	}
