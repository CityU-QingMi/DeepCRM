	@Test
	public void testModFlagProperties() {
		assertEquals(
				TestTools.makeSet( "str1_CHANGED", "long1_CHANGED" ),
				TestTools.extractModProperties(
						metadata().getEntityBinding(
								"org.hibernate.envers.test.integration.basic.BasicTestEntity1_AUD"
						),
						"_CHANGED"
				)
		);
	}
