	@Test
	public void setPrimitiveProperties() {
		NumberPropertyBean target = new NumberPropertyBean();
		AbstractPropertyAccessor accessor = createAccessor(target);

		String byteValue = " " + Byte.MAX_VALUE + " ";
		String shortValue = " " + Short.MAX_VALUE + " ";
		String intValue = " " + Integer.MAX_VALUE + " ";
		String longValue = " " + Long.MAX_VALUE + " ";
		String floatValue = " " + Float.MAX_VALUE + " ";
		String doubleValue = " " + Double.MAX_VALUE + " ";

		accessor.setPropertyValue("myPrimitiveByte", byteValue);
		accessor.setPropertyValue("myByte", byteValue);

		accessor.setPropertyValue("myPrimitiveShort", shortValue);
		accessor.setPropertyValue("myShort", shortValue);

		accessor.setPropertyValue("myPrimitiveInt", intValue);
		accessor.setPropertyValue("myInteger", intValue);

		accessor.setPropertyValue("myPrimitiveLong", longValue);
		accessor.setPropertyValue("myLong", longValue);

		accessor.setPropertyValue("myPrimitiveFloat", floatValue);
		accessor.setPropertyValue("myFloat", floatValue);

		accessor.setPropertyValue("myPrimitiveDouble", doubleValue);
		accessor.setPropertyValue("myDouble", doubleValue);

		assertEquals(Byte.MAX_VALUE, target.getMyPrimitiveByte());
		assertEquals(Byte.MAX_VALUE, target.getMyByte().byteValue());

		assertEquals(Short.MAX_VALUE, target.getMyPrimitiveShort());
		assertEquals(Short.MAX_VALUE, target.getMyShort().shortValue());

		assertEquals(Integer.MAX_VALUE, target.getMyPrimitiveInt());
		assertEquals(Integer.MAX_VALUE, target.getMyInteger().intValue());

		assertEquals(Long.MAX_VALUE, target.getMyPrimitiveLong());
		assertEquals(Long.MAX_VALUE, target.getMyLong().longValue());

		assertEquals(Float.MAX_VALUE, target.getMyPrimitiveFloat(), 0.001);
		assertEquals(Float.MAX_VALUE, target.getMyFloat().floatValue(), 0.001);

		assertEquals(Double.MAX_VALUE, target.getMyPrimitiveDouble(), 0.001);
		assertEquals(Double.MAX_VALUE, target.getMyDouble().doubleValue(), 0.001);

	}
