	@Test
	@RequiresDialectFeature( DialectChecks.SupportsRowValueConstructorSyntaxInInListCheck.class )
    public void testRowValueConstructorSyntaxInInList() {
		QueryTranslatorImpl translator = createNewQueryTranslator("from LineItem l where l.id in (?)");
		assertInExist(" 'in' should be kept, since the dialect supports this syntax", true, translator);
		translator = createNewQueryTranslator("from LineItem l where l.id in ?");
		assertInExist(" 'in' should be kept, since the dialect supports this syntax", true, translator);
		translator = createNewQueryTranslator("from LineItem l where l.id in (('a1',1,'b1'),('a2',2,'b2'))");
		assertInExist(" 'in' should be kept, since the dialect supports this syntax", true,translator);
		translator = createNewQueryTranslator("from Animal a where a.id in (?)");
		assertInExist("only translated tuple has 'in' syntax", true, translator);
		translator = createNewQueryTranslator("from Animal a where a.id in ?");
		assertInExist("only translated tuple has 'in' syntax", true, translator);
		translator = createNewQueryTranslator("from LineItem l where l.id in (select a1 from Animal a1 left join a1.offspring o where a1.id = 1)");
		assertInExist("do not translate sub-queries", true, translator);
    }
