	@Override
	public void onSetUp() throws Exception {
		target = new JmxTestBean();
		target.setAge(100);
		target.setName("Rob Harrop");

		MBeanExporter adapter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<>();
		beans.put(OBJECT_NAME, target);
		adapter.setServer(getServer());
		adapter.setBeans(beans);
		adapter.setAssembler(new ProxyTestAssembler());
		start(adapter);
	}
