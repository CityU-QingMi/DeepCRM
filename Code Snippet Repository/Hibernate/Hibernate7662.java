	@Test
	public void testSequenceGenerationExtractor() {
		final Dialect dialect = getDialect();
		assertThat(
				dialect.getQuerySequencesString(),
				is( expectedQuerySequencesString() )
		);
		assertThat(
				dialect.getSequenceInformationExtractor(),
				instanceOf( expectedSequenceInformationExtractor() )
		);
	}
