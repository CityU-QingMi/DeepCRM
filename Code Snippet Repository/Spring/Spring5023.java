	@Test
	public void isAssignableFromForDifferentClassesWithGenerics() throws Exception {
		ResolvableType extendsCharSequenceCollection = ResolvableType.forField(AssignmentBase.class.getField("collectionxc"), Assignment.class);
		ResolvableType charSequenceCollection = ResolvableType.forField(AssignmentBase.class.getField("collectionc"), Assignment.class);
		ResolvableType charSequenceList = ResolvableType.forField(AssignmentBase.class.getField("listc"), Assignment.class);
		ResolvableType extendsCharSequenceList = ResolvableType.forField(AssignmentBase.class.getField("listxc"), Assignment.class);
		ResolvableType extendsStringList = ResolvableType.forField(AssignmentBase.class.getField("listxs"), Assignment.class);

		assertAssignable(extendsCharSequenceCollection, charSequenceCollection, charSequenceList, extendsCharSequenceList, extendsStringList)
				.equalTo(true, true, true, true);
		assertAssignable(charSequenceCollection, charSequenceList, extendsCharSequenceList, extendsStringList)
				.equalTo(true, false, false);
		assertAssignable(charSequenceList, extendsCharSequenceCollection, charSequenceCollection)
				.equalTo(false, false);
		assertAssignable(extendsCharSequenceList, extendsCharSequenceCollection, charSequenceCollection)
				.equalTo(false, false);
		assertAssignable(extendsStringList, charSequenceCollection, charSequenceList, extendsCharSequenceList)
				.equalTo(false, false, false);
	}
