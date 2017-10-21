	private String discriminatorFilterFragment(String alias, Set<String> treatAsDeclarations) {
		final boolean hasTreatAs = treatAsDeclarations != null && !treatAsDeclarations.isEmpty();

		if ( !needsDiscriminator() && !hasTreatAs ) {
			return "";
		}

		final InFragment frag = new InFragment();
		if ( isDiscriminatorFormula() ) {
			frag.setFormula( alias, getDiscriminatorFormulaTemplate() );
		}
		else {
			frag.setColumn( alias, getDiscriminatorColumnName() );
		}

		if ( hasTreatAs ) {
			frag.addValues( decodeTreatAsRequests( treatAsDeclarations ) );
		}
		else {
			frag.addValues( fullDiscriminatorValues() );
		}

		return " and " + frag.toFragmentString();
	}
