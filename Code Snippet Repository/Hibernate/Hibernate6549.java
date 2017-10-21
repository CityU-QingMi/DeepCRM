	@Test
	public void testSameMappingValues() {
		RootClass forest = (RootClass) metadata.getEntityBinding( Forest.class.getName() );
		RootClass forest2 = (RootClass) metadata.getEntityBinding( Forest2.class.getName() );
		assertEquals( forest.useDynamicInsert(), forest2.useDynamicInsert() );
		assertEquals( forest.useDynamicUpdate(), forest2.useDynamicUpdate() );
		assertEquals( forest.hasSelectBeforeUpdate(), forest2.hasSelectBeforeUpdate() );
		assertEquals( forest.getOptimisticLockStyle(), forest2.getOptimisticLockStyle() );
		assertEquals( forest.isExplicitPolymorphism(), forest2.isExplicitPolymorphism() );
	}
