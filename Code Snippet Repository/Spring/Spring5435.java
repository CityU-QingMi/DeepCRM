	@Test
	public void copyRecursively() throws Exception {
		File src = new File("./tmp/src");
		File child = new File(src, "child");
		File grandchild = new File(child, "grandchild");

		grandchild.mkdirs();

		File bar = new File(child, "bar.txt");
		bar.createNewFile();

		assertTrue(src.exists());
		assertTrue(child.exists());
		assertTrue(grandchild.exists());
		assertTrue(bar.exists());

		File dest = new File("./dest");
		FileSystemUtils.copyRecursively(src, dest);

		assertTrue(dest.exists());
		assertTrue(new File(dest, child.getName()).exists());

		FileSystemUtils.deleteRecursively(src);
		assertFalse(src.exists());
	}
