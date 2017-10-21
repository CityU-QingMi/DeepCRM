	private void setupServer(AnnotationMBeanExporter exporter, AnnotationAttributes enableMBeanExport) {
		String server = enableMBeanExport.getString("server");
		if (StringUtils.hasLength(server) && this.environment != null) {
			server = this.environment.resolvePlaceholders(server);
		}
		if (StringUtils.hasText(server)) {
			Assert.state(this.beanFactory != null, "No BeanFactory set");
			exporter.setServer(this.beanFactory.getBean(server, MBeanServer.class));
		}
		else {
			SpecificPlatform specificPlatform = SpecificPlatform.get();
			if (specificPlatform != null) {
				MBeanServer mbeanServer = specificPlatform.getMBeanServer();
				if (mbeanServer != null) {
					exporter.setServer(mbeanServer);
				}
			}
		}
	}
