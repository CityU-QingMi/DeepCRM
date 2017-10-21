	@Test
	public void isAssignableFromForArrays() throws Exception {
		ResolvableType object = ResolvableType.forField(AssignmentBase.class.getField("o"), Assignment.class);
		ResolvableType objectArray = ResolvableType.forField(AssignmentBase.class.getField("oarray"), Assignment.class);
		ResolvableType charSequenceArray = ResolvableType.forField(AssignmentBase.class.getField("carray"), Assignment.class);
		ResolvableType stringArray = ResolvableType.forField(AssignmentBase.class.getField("sarray"), Assignment.class);

		assertAssignable(object, objectArray, charSequenceArray, stringArray).
				equalTo(true, true, true);
		assertAssignable(objectArray, object, objectArray, charSequenceArray, stringArray).
				equalTo(false, true, true, true);
		assertAssignable(charSequenceArray, object, objectArray, charSequenceArray, stringArray).
				equalTo(false, false, true, true);
		assertAssignable(stringArray, object, objectArray, charSequenceArray, stringArray).
				equalTo(false, false, false, true);
	}
