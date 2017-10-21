	public void applyTreatAsDeclarations(Set<String> treatAsDeclarations) {
		if ( treatAsDeclarations != null && !treatAsDeclarations.isEmpty() ) {
			if ( this.treatAsDeclarations == null ) {
				this.treatAsDeclarations = new HashSet<String>();
			}

			for ( String treatAsSubclassName : treatAsDeclarations ) {
				try {
					EntityPersister subclassPersister = fromElement.getSessionFactoryHelper().requireClassPersister(
							treatAsSubclassName
					);
					this.treatAsDeclarations.add( subclassPersister.getEntityName() );
				}
				catch (SemanticException e) {
					throw new QueryException( "Unable to locate persister for subclass named in TREAT-AS : " + treatAsSubclassName );
				}
			}

			if ( joinSequence != null ) {
				joinSequence.applyTreatAsDeclarations( this.treatAsDeclarations );
			}
		}
	}
