	@Test
	public void testUserCreatedMBeanRegWithDynamicMBean() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("spring:name=dynBean", new TestDynamicMBean());

		InvokeDetectAssembler asm = new InvokeDetectAssembler();

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(map);
		exporter.setAssembler(asm);

		try {
			start(exporter);
			Object name = server.getAttribute(ObjectNameManager.getInstance("spring:name=dynBean"), "Name");
			assertEquals("The name attribute is incorrect", "Rob Harrop", name);
			assertFalse("Assembler should not have been invoked", asm.invoked);
		}
		finally {
   			exporter.destroy();
		}
	}
