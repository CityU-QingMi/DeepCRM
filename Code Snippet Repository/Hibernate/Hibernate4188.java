	private String generateSequentialSelect(Loadable persister) {
		//if ( this==persister || !hasSequentialSelects ) return null;

		//note that this method could easily be moved up to BasicEntityPersister,
		//if we ever needed to reuse it from other subclasses

		//figure out which tables need to be fetched
		AbstractEntityPersister subclassPersister = (AbstractEntityPersister) persister;
		HashSet<Integer> tableNumbers = new HashSet<Integer>();
		String[] props = subclassPersister.getPropertyNames();
		String[] classes = subclassPersister.getPropertySubclassNames();
		for ( int i = 0; i < props.length; i++ ) {
			int propTableNumber = getSubclassPropertyTableNumber( props[i], classes[i] );
			if ( isSubclassTableSequentialSelect( propTableNumber ) && !isSubclassTableLazy( propTableNumber ) ) {
				tableNumbers.add( propTableNumber );
			}
		}
		if ( tableNumbers.isEmpty() ) {
			return null;
		}

		//figure out which columns are needed
		ArrayList<Integer> columnNumbers = new ArrayList<Integer>();
		final int[] columnTableNumbers = getSubclassColumnTableNumberClosure();
		for ( int i = 0; i < getSubclassColumnClosure().length; i++ ) {
			if ( tableNumbers.contains( columnTableNumbers[i] ) ) {
				columnNumbers.add( i );
			}
		}

		//figure out which formulas are needed
		ArrayList<Integer> formulaNumbers = new ArrayList<Integer>();
		final int[] formulaTableNumbers = getSubclassColumnTableNumberClosure();
		for ( int i = 0; i < getSubclassFormulaTemplateClosure().length; i++ ) {
			if ( tableNumbers.contains( formulaTableNumbers[i] ) ) {
				formulaNumbers.add( i );
			}
		}

		//render the SQL
		return renderSelect(
				ArrayHelper.toIntArray( tableNumbers ),
				ArrayHelper.toIntArray( columnNumbers ),
				ArrayHelper.toIntArray( formulaNumbers )
		);
	}
