	@Test
	void classNameSourceWithFilePosition() {
		String testClassName = "com.unknown.mypackage.ClassByName";
		FilePosition position = FilePosition.from(42, 23);
		ClassSource source = ClassSource.from(testClassName, position);

		assertThat(source.getClassName()).isEqualTo(testClassName);
		assertThat(source.getPosition()).isNotEmpty();
		assertThat(source.getPosition()).hasValue(position);
	}
