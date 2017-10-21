	@Test
	public void testPersistenceUnitRootUrlWithJar() throws Exception {
		ClassPathResource archive = new ClassPathResource("/org/springframework/orm/jpa/jpa-archive.jar");
		String newRoot = "jar:" + archive.getURL().toExternalForm() + "!/META-INF/persist.xml";
		Resource insideArchive = new UrlResource(newRoot);
		// make sure the location actually exists
		assertTrue(insideArchive.exists());
		URL url = PersistenceUnitReader.determinePersistenceUnitRootUrl(insideArchive);
		assertTrue("the archive location should have been returned", archive.getURL().sameFile(url));
	}
