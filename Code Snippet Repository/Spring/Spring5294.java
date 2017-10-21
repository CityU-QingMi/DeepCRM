	@Test
	public void testClassPathResource() throws IOException {
		Resource resource = new ClassPathResource("org/springframework/core/io/Resource.class");
		doTestResource(resource);
		Resource resource2 = new ClassPathResource("org/springframework/core/../core/io/./Resource.class");
		assertEquals(resource, resource2);
		Resource resource3 = new ClassPathResource("org/springframework/core/").createRelative("../core/io/./Resource.class");
		assertEquals(resource, resource3);

		// Check whether equal/hashCode works in a HashSet.
		HashSet<Resource> resources = new HashSet<>();
		resources.add(resource);
		resources.add(resource2);
		assertEquals(1, resources.size());
	}
