	@Test
	public void resourceInjection() throws IOException {
		System.setProperty("logfile", "do_not_delete_me.txt");
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ResourceInjectionBean.class)) {
			ResourceInjectionBean resourceInjectionBean = ac.getBean(ResourceInjectionBean.class);
			Resource resource = new ClassPathResource("do_not_delete_me.txt");
			assertEquals(resource, resourceInjectionBean.resource);
			assertEquals(resource.getURL(), resourceInjectionBean.url);
			assertEquals(resource.getURI(), resourceInjectionBean.uri);
			assertEquals(resource.getFile(), resourceInjectionBean.file);
			assertArrayEquals(FileCopyUtils.copyToByteArray(resource.getInputStream()),
					FileCopyUtils.copyToByteArray(resourceInjectionBean.inputStream));
			assertEquals(FileCopyUtils.copyToString(new EncodedResource(resource).getReader()),
					FileCopyUtils.copyToString(resourceInjectionBean.reader));
		}
		finally {
			System.getProperties().remove("logfile");
		}
	}
