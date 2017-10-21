	@Test
	public void testGetTypeVariableMap() throws Exception {
		Map<TypeVariable, Type> map;

		map = GenericTypeResolver.getTypeVariableMap(MySimpleInterfaceType.class);
		assertThat(map.toString(), equalTo("{T=class java.lang.String}"));

		map = GenericTypeResolver.getTypeVariableMap(MyCollectionInterfaceType.class);
		assertThat(map.toString(), equalTo("{T=java.util.Collection<java.lang.String>}"));

		map = GenericTypeResolver.getTypeVariableMap(MyCollectionSuperclassType.class);
		assertThat(map.toString(), equalTo("{T=java.util.Collection<java.lang.String>}"));

		map = GenericTypeResolver.getTypeVariableMap(MySimpleTypeWithMethods.class);
		assertThat(map.toString(), equalTo("{T=class java.lang.Integer}"));

		map = GenericTypeResolver.getTypeVariableMap(TopLevelClass.class);
		assertThat(map.toString(), equalTo("{}"));

		map = GenericTypeResolver.getTypeVariableMap(TypedTopLevelClass.class);
		assertThat(map.toString(), equalTo("{T=class java.lang.Integer}"));

		map = GenericTypeResolver.getTypeVariableMap(TypedTopLevelClass.TypedNested.class);
		assertThat(map.size(), equalTo(2));
		Type t = null;
		Type x = null;
		for (Map.Entry<TypeVariable, Type> entry : map.entrySet()) {
			if (entry.getKey().toString().equals("T")) {
				t = entry.getValue();
			}
			else {
				x = entry.getValue();
			}
		}
		assertThat(t, equalTo((Type) Integer.class));
		assertThat(x, equalTo((Type) Long.class));
	}
