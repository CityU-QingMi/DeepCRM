    @SuppressWarnings("")
    @Test
    public void testFindCharSequenceConverterUsingStringConverter() throws Exception {
        final TypeConverter<CharSequence> converter = (TypeConverter<CharSequence>)
            TypeConverterRegistry.getInstance().findCompatibleConverter(CharSequence.class);
        assertNotNull(converter);
        assertThat(converter, instanceOf(TypeConverters.StringConverter.class));
        final CharSequence expected = "This is a test sequence of characters";
        final CharSequence actual = converter.convert(expected.toString());
        assertEquals(expected, actual);
    }
