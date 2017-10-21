	@Test
	public void isAssignableFromForClassAndSimpleVariable() throws Exception {
		ResolvableType objectType = ResolvableType.forClass(Object.class);
		ResolvableType charSequenceType = ResolvableType.forClass(CharSequence.class);
		ResolvableType stringType = ResolvableType.forClass(String.class);

		ResolvableType objectVariable = ResolvableType.forField(AssignmentBase.class.getField("o"), Assignment.class);
		ResolvableType charSequenceVariable = ResolvableType.forField(AssignmentBase.class.getField("c"), Assignment.class);
		ResolvableType stringVariable = ResolvableType.forField(AssignmentBase.class.getField("s"), Assignment.class);

		assertAssignable(objectType, objectVariable, charSequenceVariable, stringVariable).equalTo(true, true, true);
		assertAssignable(charSequenceType, objectVariable, charSequenceVariable, stringVariable).equalTo(false, true, true);
		assertAssignable(stringType, objectVariable, charSequenceVariable, stringVariable).equalTo(false, false, true);

		assertAssignable(objectVariable, objectType, charSequenceType, stringType).equalTo(true, true, true);
		assertAssignable(charSequenceVariable, objectType, charSequenceType, stringType).equalTo(false, true, true);
		assertAssignable(stringVariable, objectType, charSequenceType, stringType).equalTo(false, false, true);

		assertAssignable(objectVariable, objectVariable, charSequenceVariable, stringVariable).equalTo(true, true, true);
		assertAssignable(charSequenceVariable, objectVariable, charSequenceVariable, stringVariable).equalTo(false, true, true);
		assertAssignable(stringVariable, objectVariable, charSequenceVariable, stringVariable).equalTo(false, false, true);
	}
