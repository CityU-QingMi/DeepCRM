	private void parseEmbeddableAttributes(EmbeddableAttributes attributes) {
		if ( attributes == null ) {
			return;
		}
		for ( Basic basic : attributes.getBasic() ) {
			parseBasic( basic );
		}

		for ( ManyToOne manyToOne : attributes.getManyToOne() ) {
			parseManyToOne( manyToOne );
		}

		for ( OneToOne oneToOne : attributes.getOneToOne() ) {
			parseOneToOne( oneToOne );
		}

		for ( ManyToMany manyToMany : attributes.getManyToMany() ) {
			if ( parseManyToMany( manyToMany ) ) {
				break;
			}
		}

		for ( OneToMany oneToMany : attributes.getOneToMany() ) {
			if ( parseOneToMany( oneToMany ) ) {
				break;
			}
		}

		for ( ElementCollection collection : attributes.getElementCollection() ) {
			if ( parseElementCollection( collection ) ) {
				break;
			}
		}
	}
