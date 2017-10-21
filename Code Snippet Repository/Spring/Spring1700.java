	@Test
	public void testDefaultNumberEditor() {
		NumberTestBean tb = new NumberTestBean();
		BeanWrapper bw = new BeanWrapperImpl(tb);

		bw.setPropertyValue("short1", "1");
		bw.setPropertyValue("short2", "2");
		bw.setPropertyValue("int1", "7");
		bw.setPropertyValue("int2", "8");
		bw.setPropertyValue("long1", "5");
		bw.setPropertyValue("long2", "6");
		bw.setPropertyValue("bigInteger", "3");
		bw.setPropertyValue("float1", "7.1");
		bw.setPropertyValue("float2", "8.1");
		bw.setPropertyValue("double1", "5.1");
		bw.setPropertyValue("double2", "6.1");
		bw.setPropertyValue("bigDecimal", "4.5");

		assertTrue("Correct short1 value", new Short("1").equals(bw.getPropertyValue("short1")));
		assertTrue("Correct short1 value", tb.getShort1() == 1);
		assertTrue("Correct short2 value", new Short("2").equals(bw.getPropertyValue("short2")));
		assertTrue("Correct short2 value", new Short("2").equals(tb.getShort2()));
		assertTrue("Correct int1 value", new Integer("7").equals(bw.getPropertyValue("int1")));
		assertTrue("Correct int1 value", tb.getInt1() == 7);
		assertTrue("Correct int2 value", new Integer("8").equals(bw.getPropertyValue("int2")));
		assertTrue("Correct int2 value", new Integer("8").equals(tb.getInt2()));
		assertTrue("Correct long1 value", new Long("5").equals(bw.getPropertyValue("long1")));
		assertTrue("Correct long1 value", tb.getLong1() == 5);
		assertTrue("Correct long2 value", new Long("6").equals(bw.getPropertyValue("long2")));
		assertTrue("Correct long2 value", new Long("6").equals(tb.getLong2()));
		assertTrue("Correct bigInteger value", new BigInteger("3").equals(bw.getPropertyValue("bigInteger")));
		assertTrue("Correct bigInteger value", new BigInteger("3").equals(tb.getBigInteger()));
		assertTrue("Correct float1 value", new Float("7.1").equals(bw.getPropertyValue("float1")));
		assertTrue("Correct float1 value", new Float("7.1").equals(new Float(tb.getFloat1())));
		assertTrue("Correct float2 value", new Float("8.1").equals(bw.getPropertyValue("float2")));
		assertTrue("Correct float2 value", new Float("8.1").equals(tb.getFloat2()));
		assertTrue("Correct double1 value", new Double("5.1").equals(bw.getPropertyValue("double1")));
		assertTrue("Correct double1 value", tb.getDouble1() == 5.1);
		assertTrue("Correct double2 value", new Double("6.1").equals(bw.getPropertyValue("double2")));
		assertTrue("Correct double2 value", new Double("6.1").equals(tb.getDouble2()));
		assertTrue("Correct bigDecimal value", new BigDecimal("4.5").equals(bw.getPropertyValue("bigDecimal")));
		assertTrue("Correct bigDecimal value", new BigDecimal("4.5").equals(tb.getBigDecimal()));
	}
