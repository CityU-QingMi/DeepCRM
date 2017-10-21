	private static JaxbHbmLazyWithNoProxyEnum interpretLazy(
			MappingDocument mappingDocument,
			JaxbHbmCompositeKeyManyToOneType keyManyToOne) {
		if ( keyManyToOne.getLazy() == null ) {
			return null;
		}
		else if ( keyManyToOne.getLazy() == JaxbHbmLazyEnum.FALSE ) {
			return JaxbHbmLazyWithNoProxyEnum.FALSE;
		}
		else if ( keyManyToOne.getLazy() == JaxbHbmLazyEnum.PROXY ) {
			return JaxbHbmLazyWithNoProxyEnum.PROXY;
		}

		throw new MappingException(
				"Unrecognized lazy value [" + keyManyToOne.getLazy().name() +
						"] specified for key-many-to-one [" + keyManyToOne.getName() + "]",
				mappingDocument.getOrigin()
		);
	}
