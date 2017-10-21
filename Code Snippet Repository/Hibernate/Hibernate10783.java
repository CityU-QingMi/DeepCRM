	@Test
	public void testModFlagProperties() {
		assertEquals(
				TestTools.makeSet( "comp1_MOD" ),
				TestTools.extractModProperties(
						metadata().getEntityBinding(
								"org.hibernate.envers.test.entities.components.ComponentTestEntity_AUD"
						)
				)
		);
	}
