	@Test
	@SuppressWarnings("")
	public void testGetExecutorQualifier() throws SecurityException, NoSuchMethodException {
		AnnotationAsyncExecutionInterceptor i = new AnnotationAsyncExecutionInterceptor(null);
		{ // method level
			class C { @Async("qMethod") void m() { } }
			assertThat(i.getExecutorQualifier(C.class.getDeclaredMethod("m")), is("qMethod"));
		}
		{ // class level
			@Async("qClass") class C { void m() { } }
			assertThat(i.getExecutorQualifier(C.class.getDeclaredMethod("m")), is("qClass"));
		}
		{ // method and class level -> method value overrides
			@Async("qClass") class C { @Async("qMethod") void m() { } }
			assertThat(i.getExecutorQualifier(C.class.getDeclaredMethod("m")), is("qMethod"));
		}
		{ // method and class level -> method value, even if empty, overrides
			@Async("qClass") class C { @Async void m() { } }
			assertThat(i.getExecutorQualifier(C.class.getDeclaredMethod("m")), is(""));
		}
		{ // meta annotation with qualifier
			@MyAsync class C { void m() { } }
			assertThat(i.getExecutorQualifier(C.class.getDeclaredMethod("m")), is("qMeta"));
		}
	}
