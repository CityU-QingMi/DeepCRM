	@Test
	public void testCrazyIdFieldNames() {
		DotNode.useThetaStyleImplicitJoins = true;
		// only regress against non-scalar forms as there appears to be a bug in the classic translator
		// in regards to this issue also.  Specifically, it interprets the wrong return type, though it gets
		// the sql "correct" :/

		String hql = "select e.heresAnotherCrazyIdFieldName from MoreCrazyIdFieldNameStuffEntity e where e.heresAnotherCrazyIdFieldName is not null";
		assertTranslation( hql, new HashMap(), false, null );

	    hql = "select e.heresAnotherCrazyIdFieldName.heresAnotherCrazyIdFieldName from MoreCrazyIdFieldNameStuffEntity e where e.heresAnotherCrazyIdFieldName is not null";
		assertTranslation( hql, new HashMap(), false, null );

		DotNode.useThetaStyleImplicitJoins = false;
	}
