	@Test
	public void testWithFallThrough() throws Exception {
		InterfaceBasedMBeanInfoAssembler assembler =
				getWithMapping("foobar", "org.springframework.jmx.export.assembler.ICustomJmxBean");
		assembler.setManagedInterfaces(new Class<?>[] {IAdditionalTestMethods.class});

		ModelMBeanInfo inf = assembler.getMBeanInfo(getBean(), getObjectName());
		MBeanAttributeInfo attr = inf.getAttribute("NickName");

		assertNickName(attr);
	}
