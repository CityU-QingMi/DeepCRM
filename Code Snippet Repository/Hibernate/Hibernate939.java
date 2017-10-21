	public static void processNamedQuery(
			HbmLocalMetadataBuildingContext context,
			JaxbHbmNamedQueryType namedQueryBinding,
			String prefix) {
		String query = null;
		java.util.Map<String,String> parameterTypeMap = null;

		for ( Object content : namedQueryBinding.getContent() ) {
			if ( String.class.isInstance( content ) ) {
				String trimmed = ((String)content).trim();
				if (!"".equals(trimmed)) {
					query = trimmed;
				}
			}
			else {
				final JaxbHbmQueryParamType paramTypeBinding = 
						(JaxbHbmQueryParamType)((JAXBElement)content).getValue();
				if ( parameterTypeMap == null ) {
					parameterTypeMap = new HashMap<String,String>();
				}
				parameterTypeMap.put( paramTypeBinding.getName(), paramTypeBinding.getType() );
			}
		}

		if ( query == null ) {
			throw new org.hibernate.boot.MappingException(
					String.format(
							"Named query [%s] did not specify query string",
							namedQueryBinding.getName()
					),
					context.getOrigin()
			);
		}
		context.getMetadataCollector().addNamedQuery(
				new NamedQueryDefinitionBuilder()
						.setName( prefix + namedQueryBinding.getName() )
						.setQuery( query )
						.setComment( namedQueryBinding.getComment() )
						.setCacheable( namedQueryBinding.isCacheable() )
						.setCacheMode( namedQueryBinding.getCacheMode() )
						.setCacheRegion( namedQueryBinding.getCacheRegion() )
						.setTimeout( namedQueryBinding.getTimeout() )
						.setReadOnly( namedQueryBinding.isReadOnly() )
						.setFlushMode( namedQueryBinding.getFlushMode() )
						.setFetchSize( namedQueryBinding.getFetchSize() )
						.setParameterTypes( parameterTypeMap )
						.createNamedQueryDefinition()
		);
	}
