	@Test
	public void getInterfaces() throws Exception {
		ResolvableType type = ResolvableType.forClass(ExtendsList.class);
		assertThat(type.getInterfaces().length, equalTo(0));
		SortedSet<String> interfaces = new TreeSet<>();
		for (ResolvableType interfaceType : type.getSuperType().getInterfaces()) {
			interfaces.add(interfaceType.toString());
		}
		assertThat(interfaces.toString(), equalTo(
				  "["
				+ "java.io.Serializable, "
				+ "java.lang.Cloneable, "
				+ "java.util.List<java.lang.CharSequence>, "
				+ "java.util.RandomAccess"
				+ "]"));
	}
