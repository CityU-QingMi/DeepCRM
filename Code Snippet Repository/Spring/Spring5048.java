	@Test
	public void findAndSynthesizeAnnotationAttributesOnClassWithAttributeAliasesInTargetAnnotation() {
		String qualifier = "aliasForQualifier";

		// 1) Find and merge AnnotationAttributes from the annotation hierarchy
		AnnotationAttributes attributes = findMergedAnnotationAttributes(
				AliasedTransactionalComponentClass.class, AliasedTransactional.class);
		assertNotNull("@AliasedTransactional on AliasedTransactionalComponentClass.", attributes);

		// 2) Synthesize the AnnotationAttributes back into the target annotation
		AliasedTransactional annotation = AnnotationUtils.synthesizeAnnotation(attributes,
				AliasedTransactional.class, AliasedTransactionalComponentClass.class);
		assertNotNull(annotation);

		// 3) Verify that the AnnotationAttributes and synthesized annotation are equivalent
		assertEquals("TX value via attributes.", qualifier, attributes.getString("value"));
		assertEquals("TX value via synthesized annotation.", qualifier, annotation.value());
		assertEquals("TX qualifier via attributes.", qualifier, attributes.getString("qualifier"));
		assertEquals("TX qualifier via synthesized annotation.", qualifier, annotation.qualifier());
	}
