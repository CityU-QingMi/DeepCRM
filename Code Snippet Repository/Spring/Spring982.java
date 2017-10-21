	@Test
	public void setNumberProperties() {
		NumberTestBean target = new NumberTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);

		try {
			accessor.setPropertyValue("short2", "2");
			accessor.setPropertyValue("int2", "8");
			accessor.setPropertyValue("long2", "6");
			accessor.setPropertyValue("bigInteger", "3");
			accessor.setPropertyValue("float2", "8.1");
			accessor.setPropertyValue("double2", "6.1");
			accessor.setPropertyValue("bigDecimal", "4.0");
		}
		catch (BeansException ex) {
			fail("Should not throw BeansException: " + ex.getMessage());
		}

		assertTrue("Correct short2 value", new Short("2").equals(accessor.getPropertyValue("short2")));
		assertTrue("Correct short2 value", new Short("2").equals(target.getShort2()));
		assertTrue("Correct int2 value", new Integer("8").equals(accessor.getPropertyValue("int2")));
		assertTrue("Correct int2 value", new Integer("8").equals(target.getInt2()));
		assertTrue("Correct long2 value", new Long("6").equals(accessor.getPropertyValue("long2")));
		assertTrue("Correct long2 value", new Long("6").equals(target.getLong2()));
		assertTrue("Correct bigInteger value", new BigInteger("3").equals(accessor.getPropertyValue("bigInteger")));
		assertTrue("Correct bigInteger value", new BigInteger("3").equals(target.getBigInteger()));
		assertTrue("Correct float2 value", new Float("8.1").equals(accessor.getPropertyValue("float2")));
		assertTrue("Correct float2 value", new Float("8.1").equals(target.getFloat2()));
		assertTrue("Correct double2 value", new Double("6.1").equals(accessor.getPropertyValue("double2")));
		assertTrue("Correct double2 value", new Double("6.1").equals(target.getDouble2()));
		assertTrue("Correct bigDecimal value", new BigDecimal("4.0").equals(accessor.getPropertyValue("bigDecimal")));
		assertTrue("Correct bigDecimal value", new BigDecimal("4.0").equals(target.getBigDecimal()));
	}
