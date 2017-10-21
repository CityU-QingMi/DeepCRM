	@Test
	public void lastModifiedWorksWithResourceThatDoesNotSupportFileBasedReading() throws Exception {
		Resource resource = mock(Resource.class);
		// underlying File is asked for so that the last modified time can be checked...
		// And then mock the file changing; i.e. the File says it has been modified
		given(resource.lastModified()).willReturn(100L, 100L, 200L);
		// does not support File-based reading; delegates to InputStream-style reading...
		//resource.getFile();
		//mock.setThrowable(new FileNotFoundException());
		given(resource.getInputStream()).willReturn(StreamUtils.emptyInput());

		ResourceScriptSource scriptSource = new ResourceScriptSource(resource);
		assertTrue("ResourceScriptSource must start off in the 'isModified' state (it obviously isn't).", scriptSource.isModified());
		scriptSource.getScriptAsString();
		assertFalse("ResourceScriptSource must not report back as being modified if the underlying File resource is not reporting a changed lastModified time.", scriptSource.isModified());
		// Must now report back as having been modified
		assertTrue("ResourceScriptSource must report back as being modified if the underlying File resource is reporting a changed lastModified time.", scriptSource.isModified());
	}
