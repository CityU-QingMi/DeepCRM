	private void evaluateAssignment(AST eq, Queryable persister, int targetIndex) {
		if ( persister.isMultiTable() ) {
			// no need to even collect this information if the persister is considered multi-table
			AssignmentSpecification specification = new AssignmentSpecification( eq, persister );
			if ( targetIndex >= 0 ) {
				assignmentSpecifications.add( targetIndex, specification );
			}
			else {
				assignmentSpecifications.add( specification );
			}
			numberOfParametersInSetClause += specification.getParameters().length;
		}
	}
