	@Test
	public void testAppendIdentityToObjectName() throws MalformedObjectNameException {
		ObjectName objectName = ObjectNameManager.getInstance("spring:type=Test");
		Object managedResource = new Object();
		ObjectName uniqueName = JmxUtils.appendIdentityToObjectName(objectName, managedResource);

		String typeProperty = "type";

		assertEquals("Domain of transformed name is incorrect", objectName.getDomain(), uniqueName.getDomain());
		assertEquals("Type key is incorrect", objectName.getKeyProperty(typeProperty), uniqueName.getKeyProperty("type"));
		assertEquals("Identity key is incorrect", ObjectUtils.getIdentityHexString(managedResource), uniqueName.getKeyProperty(JmxUtils.IDENTITY_OBJECT_NAME_KEY));
	}
