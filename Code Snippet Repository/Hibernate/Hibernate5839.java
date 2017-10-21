	private NestedLegacyEntity createExisitingNestedLegacyEntity() {

		ModernEntity modernEntity = new ModernEntity();
		modernEntity.setFoo(2);

		LegacyEntity legacyEntity = new LegacyEntity();
		legacyEntity.setPrimitivePk1(1);
		legacyEntity.setPrimitivePk2(2);
		legacyEntity.setFoo("Foo");

		NestedLegacyEntity nestedLegacyEntity = new NestedLegacyEntity();
		nestedLegacyEntity.setModernEntity(modernEntity);
		nestedLegacyEntity.setLegacyEntity(legacyEntity);

		return nestedLegacyEntity;
	}
