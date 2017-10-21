	public void applyTreatAsDeclarations(Set<String> treatAsDeclarations) {
		if ( treatAsDeclarations == null || treatAsDeclarations.isEmpty() ) {
			return;
		}

		if ( this.treatAsDeclarations == null ) {
			this.treatAsDeclarations = new HashSet<String>();
		}

		this.treatAsDeclarations.addAll( treatAsDeclarations );
	}
