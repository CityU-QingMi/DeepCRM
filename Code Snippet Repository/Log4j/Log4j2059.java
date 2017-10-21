    @SuppressWarnings("")
    @Test
    public void testFindEnumConverter() throws Exception {
        final TypeConverter<Foo> fooTypeConverter = (TypeConverter<Foo>)
            TypeConverterRegistry.getInstance().findCompatibleConverter(Foo.class);
        assertNotNull(fooTypeConverter);
        assertEquals(Foo.I, fooTypeConverter.convert("i"));
        assertEquals(Foo.PITY, fooTypeConverter.convert("pity"));
        assertEquals(Foo.THE, fooTypeConverter.convert("THE"));
    }
