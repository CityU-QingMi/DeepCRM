	@Test
	public void isAssignableFromForComplexWildcards() throws Exception {
		ResolvableType complex1 = ResolvableType.forField(AssignmentBase.class.getField("complexWildcard1"));
		ResolvableType complex2 = ResolvableType.forField(AssignmentBase.class.getField("complexWildcard2"));
		ResolvableType complex3 = ResolvableType.forField(AssignmentBase.class.getField("complexWildcard3"));
		ResolvableType complex4 = ResolvableType.forField(AssignmentBase.class.getField("complexWildcard4"));

		assertAssignable(complex1, complex2).equalTo(true);
		assertAssignable(complex2, complex1).equalTo(false);
		assertAssignable(complex3, complex4).equalTo(true);
		assertAssignable(complex4, complex3).equalTo(false);
	}
