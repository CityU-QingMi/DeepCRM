	private Property getRecursiveProperty(String propertyPath, Iterator iter) throws MappingException {
		Property property = null;
		StringTokenizer st = new StringTokenizer( propertyPath, ".", false );
		try {
			while ( st.hasMoreElements() ) {
				final String element = (String) st.nextElement();
				if ( property == null ) {
					Property identifierProperty = getIdentifierProperty();
					if ( identifierProperty != null && identifierProperty.getName().equals( element ) ) {
						// we have a mapped identifier property and the root of
						// the incoming property path matched that identifier
						// property
						property = identifierProperty;
					}
					else if ( identifierProperty == null && getIdentifierMapper() != null ) {
						// we have an embedded composite identifier
						try {
							identifierProperty = getProperty( element, getIdentifierMapper().getPropertyIterator() );
							if ( identifierProperty != null ) {
								// the root of the incoming property path matched one
								// of the embedded composite identifier properties
								property = identifierProperty;
							}
						}
						catch (MappingException ignore) {
							// ignore it...
						}
					}

					if ( property == null ) {
						property = getProperty( element, iter );
					}
				}
				else {
					//flat recursive algorithm
					property = ( (Component) property.getValue() ).getProperty( element );
				}
			}
		}
		catch (MappingException e) {
			throw new MappingException( "property [" + propertyPath + "] not found on entity [" + getEntityName() + "]" );
		}

		return property;
	}
