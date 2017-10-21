	private void topologicalSort( List<CopyIdentifierComponentSecondPass> sorted, Set<CopyIdentifierComponentSecondPass> toSort ) {
		while (!toSort.isEmpty()) {
			CopyIdentifierComponentSecondPass independent = null;

			searchForIndependent:
			for ( CopyIdentifierComponentSecondPass secondPass : toSort ) {
				for ( CopyIdentifierComponentSecondPass other : toSort ) {
					if (secondPass.dependentUpon( other )) {
						continue searchForIndependent;
					}
				}
				independent = secondPass;
				break;
			}
			if (independent == null) {
				throw new MappingException( "cyclic dependency in derived identities" );
			}
			toSort.remove( independent );
			sorted.add( independent );
		}
	}
