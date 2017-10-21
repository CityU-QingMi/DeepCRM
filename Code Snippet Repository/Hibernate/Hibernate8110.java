	@Test
	public void testSelectExpressions() {
		DotNode.useThetaStyleImplicitJoins = true;
		assertTranslation( "select an.mother.mother from Animal an" );
		assertTranslation( "select an.mother.mother.mother from Animal an" );
		assertTranslation( "select an.mother.mother.bodyWeight from Animal an" );
		assertTranslation( "select an.mother.zoo.id from Animal an" );
		assertTranslation( "select user.human.zoo.id from User user" );
		assertTranslation( "select u.userName, u.human.name.first from User u" );
		assertTranslation( "select u.human.name.last, u.human.name.first from User u" );
		assertTranslation( "select bar.baz.name from Bar bar" );
		assertTranslation( "select bar.baz.name, bar.baz.count from Bar bar" );
		DotNode.useThetaStyleImplicitJoins = false;
	}
