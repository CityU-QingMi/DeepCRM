	@Test(expected = IllegalStateException.class)
	public void assertHasAncestorOfTypeThrowsExceptionOnFail() throws Exception {
				Tag a = new TagA();
				Tag b = new TagB();
				Tag anotherB = new TagB();

				a.setParent(b);
				b.setParent(anotherB);

				TagUtils.assertHasAncestorOfType(a, TagC.class, "a", "c");
	}
