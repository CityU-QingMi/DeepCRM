	@Test
	public void cornerSpr8949() throws IntrospectionException {
		class A {
			@SuppressWarnings("unused")
			public boolean isTargetMethod() {
				return false;
			}
		}

		class B extends A {
			@Override
			public boolean isTargetMethod() {
				return false;
			}
		}

		BeanInfo bi = Introspector.getBeanInfo(B.class);

		// java.beans.Introspector returns the "wrong" declaring class for overridden read
		// methods, which in turn violates expectations in {@link ExtendedBeanInfo} regarding
		// method equality. Spring's {@link ClassUtils#getMostSpecificMethod(Method, Class)}
		// helps out here, and is now put into use in ExtendedBeanInfo as well.
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "targetMethod"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "targetMethod"), is(false));

		assertThat(hasReadMethodForProperty(ebi, "targetMethod"), is(true));
		assertThat(hasWriteMethodForProperty(ebi, "targetMethod"), is(false));
	}
