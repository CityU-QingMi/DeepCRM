	@Test
	public void isAssignableFromForSameClassExtendsGenerics() throws Exception {

		// Generic assignment can be a little confusing, given:
		//
		// List<CharSequence> c1, List<? extends CharSequence> c2, List<String> s;
		//
		// c2 = s; is allowed and is often used for argument input, for example
		// see List.addAll(). You can get items from c2 but you cannot add items without
		// getting a generic type 'is not applicable for the arguments' error. This makes
		// sense since if you added a StringBuffer to c2 it would break the rules on s.
		//
		// c1 = s; not allowed. Since there is no '? extends' to cause the generic
		// 'is not applicable for the arguments' error when adding (which would pollute
		// s).

		ResolvableType objectList = ResolvableType.forField(AssignmentBase.class.getField("listo"), Assignment.class);
		ResolvableType charSequenceList = ResolvableType.forField(AssignmentBase.class.getField("listc"), Assignment.class);
		ResolvableType stringList = ResolvableType.forField(AssignmentBase.class.getField("lists"), Assignment.class);
		ResolvableType extendsObjectList = ResolvableType.forField(AssignmentBase.class.getField("listxo"), Assignment.class);
		ResolvableType extendsCharSequenceList = ResolvableType.forField(AssignmentBase.class.getField("listxc"), Assignment.class);
		ResolvableType extendsStringList = ResolvableType.forField(AssignmentBase.class.getField("listxs"), Assignment.class);

		assertAssignable(objectList, extendsObjectList, extendsCharSequenceList, extendsStringList).equalTo(false, false, false);
		assertAssignable(charSequenceList, extendsObjectList, extendsCharSequenceList, extendsStringList).equalTo(false, false, false);
		assertAssignable(stringList, extendsObjectList, extendsCharSequenceList, extendsStringList).equalTo(false, false, false);
		assertAssignable(extendsObjectList, objectList, charSequenceList, stringList).equalTo(true, true, true);
		assertAssignable(extendsObjectList, extendsObjectList, extendsCharSequenceList, extendsStringList).equalTo(true, true, true);
		assertAssignable(extendsCharSequenceList, extendsObjectList, extendsCharSequenceList, extendsStringList).equalTo(false, true, true);
		assertAssignable(extendsCharSequenceList, objectList, charSequenceList, stringList).equalTo(false, true, true);
		assertAssignable(extendsStringList, extendsObjectList, extendsCharSequenceList, extendsStringList).equalTo(false, false, true);
		assertAssignable(extendsStringList, objectList, charSequenceList, stringList).equalTo(false, false, true);
	}
