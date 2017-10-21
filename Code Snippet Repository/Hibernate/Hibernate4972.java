		@Override
		public String resolveEntityName(Object entity) {
			if ( !Map.class.isInstance( entity ) ) {
				return null;
			}
			final String entityName = extractEmbeddedEntityName( (Map) entity );
			if ( entityName == null ) {
				throw new HibernateException( "Could not determine type of dynamic map entity" );
			}
			return entityName;
		}
