	private void processFkSecondPassesInOrder() {
		if ( fkSecondPassList == null || fkSecondPassList.isEmpty() ) {
			return;
		}

		// split FkSecondPass instances into primary key and non primary key FKs.
		// While doing so build a map of class names to FkSecondPass instances depending on this class.
		Map<String, Set<FkSecondPass>> isADependencyOf = new HashMap<String, Set<FkSecondPass>>();
		List<FkSecondPass> endOfQueueFkSecondPasses = new ArrayList<FkSecondPass>( fkSecondPassList.size() );
		for ( FkSecondPass sp : fkSecondPassList ) {
			if ( sp.isInPrimaryKey() ) {
				final String referenceEntityName = sp.getReferencedEntityName();
				final PersistentClass classMapping = getEntityBinding( referenceEntityName );
				final String dependentTable = classMapping.getTable().getQualifiedTableName().render();
				if ( !isADependencyOf.containsKey( dependentTable ) ) {
					isADependencyOf.put( dependentTable, new HashSet<FkSecondPass>() );
				}
				isADependencyOf.get( dependentTable ).add( sp );
			}
			else {
				endOfQueueFkSecondPasses.add( sp );
			}
		}

		// using the isADependencyOf map we order the FkSecondPass recursively instances into the right order for processing
		List<FkSecondPass> orderedFkSecondPasses = new ArrayList<FkSecondPass>( fkSecondPassList.size() );
		for ( String tableName : isADependencyOf.keySet() ) {
			buildRecursiveOrderedFkSecondPasses( orderedFkSecondPasses, isADependencyOf, tableName, tableName );
		}

		// process the ordered FkSecondPasses
		for ( FkSecondPass sp : orderedFkSecondPasses ) {
			sp.doSecondPass( getEntityBindingMap() );
		}

		processEndOfQueue( endOfQueueFkSecondPasses );

		fkSecondPassList.clear();
	}
