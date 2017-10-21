	@Bean(name = MBEAN_EXPORTER_BEAN_NAME)
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public AnnotationMBeanExporter mbeanExporter() {
		AnnotationMBeanExporter exporter = new AnnotationMBeanExporter();
		Assert.state(this.enableMBeanExport != null, "No EnableMBeanExport annotation found");
		setupDomain(exporter, this.enableMBeanExport);
		setupServer(exporter, this.enableMBeanExport);
		setupRegistrationPolicy(exporter, this.enableMBeanExport);
		return exporter;
	}
