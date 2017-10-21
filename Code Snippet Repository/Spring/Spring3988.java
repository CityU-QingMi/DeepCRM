	@Test
	public void lastModifiedWorksWithResourceThatDoesNotSupportFileBasedAccessAtAll() throws Exception {
		Resource resource = new ByteArrayResource(new byte[0]);
		ResourceScriptSource scriptSource = new ResourceScriptSource(resource);
		assertTrue("ResourceScriptSource must start off in the 'isModified' state (it obviously isn't).", scriptSource.isModified());
		scriptSource.getScriptAsString();
		assertFalse("ResourceScriptSource must not report back as being modified if the underlying File resource is not reporting a changed lastModified time.", scriptSource.isModified());
		// Must now continue to report back as not having been modified 'cos the Resource does not support access as a File (and so the lastModified date cannot be determined).
		assertFalse("ResourceScriptSource must not report back as being modified if the underlying File resource is not reporting a changed lastModified time.", scriptSource.isModified());
	}
