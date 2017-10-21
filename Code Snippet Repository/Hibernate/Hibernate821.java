	private static Map<EntityMode, String> extractTuplizers(JaxbHbmEntityBaseDefinition entityElement) {
		if ( entityElement.getTuplizer() == null ) {
			return Collections.emptyMap();
		}

		final Map<EntityMode, String> tuplizers = new HashMap<EntityMode, String>();
		for ( JaxbHbmTuplizerType tuplizerElement : entityElement.getTuplizer() ) {
			tuplizers.put(
					tuplizerElement.getEntityMode(),
					tuplizerElement.getClazz()
			);
		}
		return tuplizers;
	}
