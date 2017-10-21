	private Class determineElementClass(XClass elementXClass) {
		if ( elementXClass != null ) {
			try {
				return getContext().getBuildingOptions().getReflectionManager().toClass( elementXClass );
			}
			catch (Exception e) {
				log.debugf(
						"Unable to resolve XClass [%s] to Class for collection elements [%s]",
						elementXClass.getName(),
						collection.getRole()
				);
			}
		}

		if ( collection.getElement() != null ) {
			if ( collection.getElement().getType() != null ) {
				return collection.getElement().getType().getReturnedClass();
			}
		}

		// currently this is called from paths where the element type really should be known,
		// so log the fact that we could not resolve the collection element info
		log.debugf(
				"Unable to resolve element information for collection [%s]",
				collection.getRole()
		);
		return null;
	}
