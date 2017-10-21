	@Test
	public void isCglibRenamedMethod() throws SecurityException, NoSuchMethodException {
		@SuppressWarnings("unused")
		class C {
			public void CGLIB$m1$123() {
			}

			public void CGLIB$m1$0() {
			}

			public void CGLIB$$0() {
			}

			public void CGLIB$m1$() {
			}

			public void CGLIB$m1() {
			}

			public void m1() {
			}

			public void m1$() {
			}

			public void m1$1() {
			}
		}
		assertTrue(ReflectionUtils.isCglibRenamedMethod(C.class.getMethod("CGLIB$m1$123")));
		assertTrue(ReflectionUtils.isCglibRenamedMethod(C.class.getMethod("CGLIB$m1$0")));
		assertFalse(ReflectionUtils.isCglibRenamedMethod(C.class.getMethod("CGLIB$$0")));
		assertFalse(ReflectionUtils.isCglibRenamedMethod(C.class.getMethod("CGLIB$m1$")));
		assertFalse(ReflectionUtils.isCglibRenamedMethod(C.class.getMethod("CGLIB$m1")));
		assertFalse(ReflectionUtils.isCglibRenamedMethod(C.class.getMethod("m1")));
		assertFalse(ReflectionUtils.isCglibRenamedMethod(C.class.getMethod("m1$")));
		assertFalse(ReflectionUtils.isCglibRenamedMethod(C.class.getMethod("m1$1")));
	}
