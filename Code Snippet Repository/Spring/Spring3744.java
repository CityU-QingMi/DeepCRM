	@Test
	public void testWithFallThrough() throws Exception {
		MethodNameBasedMBeanInfoAssembler assembler =
				getWithMapping("foobar", "add,myOperation,getName,setName,getAge");
		assembler.setManagedMethods("getNickName", "setNickName");

		ModelMBeanInfo inf = assembler.getMBeanInfo(getBean(), getObjectName());
		MBeanAttributeInfo attr = inf.getAttribute("NickName");

		assertNickName(attr);
	}
