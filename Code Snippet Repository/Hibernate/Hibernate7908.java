	@Test
	@TestForIssue( jiraKey = "" )
	public void testSpecialClassPropertyReference() {
		// this is a long standing bug in Hibernate when applied to joined-subclasses;
		//  see HHH-939 for details and history
		new SyntaxChecker( "from Zoo zoo where zoo.class = PettingZoo" ).checkAll();
		new SyntaxChecker( "select a.description from Animal a where a.class = Mammal" ).checkAll();
		new SyntaxChecker( "select a.class from Animal a" ).checkAll();
		new SyntaxChecker( "from DomesticAnimal an where an.class = Dog" ).checkAll();
		new SyntaxChecker( "from Animal an where an.class = Dog" ).checkAll();
	}
