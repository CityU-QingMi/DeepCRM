	@Test
	public void naming() throws MalformedObjectNameException {
		JmxTestBean bean = new JmxTestBean();
		IdentityNamingStrategy strategy = new IdentityNamingStrategy();
		ObjectName objectName = strategy.getObjectName(bean, "null");
		assertEquals("Domain is incorrect", bean.getClass().getPackage().getName(),
				objectName.getDomain());
		assertEquals("Type property is incorrect", ClassUtils.getShortName(bean.getClass()),
				objectName.getKeyProperty(IdentityNamingStrategy.TYPE_KEY));
		assertEquals("HashCode property is incorrect", ObjectUtils.getIdentityHexString(bean),
				objectName.getKeyProperty(IdentityNamingStrategy.HASH_CODE_KEY));
	}
