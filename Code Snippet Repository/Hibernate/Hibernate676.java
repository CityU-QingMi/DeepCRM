	@Override
	public void addSecondPass(SecondPass secondPass, boolean onTopOfTheQueue) {
		if ( secondPass instanceof PkDrivenByDefaultMapsIdSecondPass ) {
			addPkDrivenByDefaultMapsIdSecondPass( (PkDrivenByDefaultMapsIdSecondPass) secondPass, onTopOfTheQueue );
		}
		else if ( secondPass instanceof SetSimpleValueTypeSecondPass ) {
			addSetSimpleValueTypeSecondPass( (SetSimpleValueTypeSecondPass) secondPass, onTopOfTheQueue );
		}
		else if ( secondPass instanceof CopyIdentifierComponentSecondPass ) {
			addCopyIdentifierComponentSecondPass( (CopyIdentifierComponentSecondPass) secondPass, onTopOfTheQueue );
		}
		else if ( secondPass instanceof FkSecondPass ) {
			addFkSecondPass( (FkSecondPass) secondPass, onTopOfTheQueue );
		}
		else if ( secondPass instanceof CreateKeySecondPass ) {
			addCreateKeySecondPass( (CreateKeySecondPass) secondPass, onTopOfTheQueue );
		}
		else if ( secondPass instanceof SecondaryTableSecondPass ) {
			addSecondaryTableSecondPass( (SecondaryTableSecondPass) secondPass, onTopOfTheQueue );
		}
		else if ( secondPass instanceof QuerySecondPass ) {
			addQuerySecondPass( (QuerySecondPass) secondPass, onTopOfTheQueue );
		}
		else if ( secondPass instanceof ImplicitColumnNamingSecondPass ) {
			addImplicitColumnNamingSecondPass( (ImplicitColumnNamingSecondPass) secondPass );
		}
		else {
			// add to the general SecondPass list
			if ( generalSecondPassList == null ) {
				generalSecondPassList = new ArrayList<SecondPass>();
			}
			addSecondPass( secondPass, generalSecondPassList, onTopOfTheQueue );
		}
	}
