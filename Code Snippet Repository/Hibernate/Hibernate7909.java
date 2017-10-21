	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubclassOrSuperclassPropertyReferenceInJoinedSubclass() {
		// this is a long standing bug in Hibernate; see HHH-1631 for details and history
		//
		// (1) pregnant is defined as a property of the class (Mammal) itself
		// (2) description is defined as a property of the superclass (Animal)
		// (3) name is defined as a property of a particular subclass (Human)

		new SyntaxChecker( "from Zoo z join z.mammals as m where m.name.first = 'John'" ).checkIterate();

		new SyntaxChecker( "from Zoo z join z.mammals as m where m.pregnant = false" ).checkAll();
		new SyntaxChecker( "select m.pregnant from Zoo z join z.mammals as m where m.pregnant = false" ).checkAll();

		new SyntaxChecker( "from Zoo z join z.mammals as m where m.description = 'tabby'" ).checkAll();
		new SyntaxChecker( "select m.description from Zoo z join z.mammals as m where m.description = 'tabby'" ).checkAll();

		new SyntaxChecker( "from Zoo z join z.mammals as m where m.name.first = 'John'" ).checkAll();
		new SyntaxChecker( "select m.name from Zoo z join z.mammals as m where m.name.first = 'John'" ).checkAll();

		new SyntaxChecker( "select m.pregnant from Zoo z join z.mammals as m" ).checkAll();
		new SyntaxChecker( "select m.description from Zoo z join z.mammals as m" ).checkAll();
		new SyntaxChecker( "select m.name from Zoo z join z.mammals as m" ).checkAll();

		new SyntaxChecker( "from DomesticAnimal da join da.owner as o where o.nickName = 'Gavin'" ).checkAll();
		new SyntaxChecker( "select da.father from DomesticAnimal da join da.owner as o where o.nickName = 'Gavin'" ).checkAll();
		new SyntaxChecker( "select da.father from DomesticAnimal da where da.owner.nickName = 'Gavin'" ).checkAll();
	}
