	private void validateLobMappingSupport(XProperty property) {
		// HHH-9834 - Sanity check
		try {
			if ( property.isAnnotationPresent( ElementCollection.class ) ) {
				if ( property.isAnnotationPresent( Lob.class ) ) {
					if ( !property.getCollectionClass().isAssignableFrom( Map.class ) ) {
						throw new MappingException(
								"@ElementCollection combined with @Lob is only supported for Map collection types."
						);
					}
				}
			}
		}
		catch ( MappingException e ) {
			throw new HibernateException(
					String.format(
							"Invalid mapping in [%s] for property [%s]",
							property.getDeclaringClass().getName(),
							property.getName()
					),
					e
			);
		}
	}
