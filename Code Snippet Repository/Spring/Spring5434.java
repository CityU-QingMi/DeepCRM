	@Test
	public void deleteRecursively() throws Exception {
		File root = new File("./tmp/root");
		File child = new File(root, "child");
		File grandchild = new File(child, "grandchild");

		grandchild.mkdirs();

		File bar = new File(child, "bar.txt");
		bar.createNewFile();

		assertTrue(root.exists());
		assertTrue(child.exists());
		assertTrue(grandchild.exists());
		assertTrue(bar.exists());

		FileSystemUtils.deleteRecursively(root);

		assertFalse(root.exists());
		assertFalse(child.exists());
		assertFalse(grandchild.exists());
		assertFalse(bar.exists());
	}
