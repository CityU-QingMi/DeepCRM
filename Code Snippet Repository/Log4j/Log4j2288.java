    @Test
    public void equalsWithNullAndNotNullValues() {
        final String location = "/to/the/file.jks";
        final char[] password = "changeit".toCharArray();
        final StoreConfiguration<Object> a = new StoreConfiguration<>(location, password);
        final StoreConfiguration<Object> b = new StoreConfiguration<>(null, (char[]) null);

        Assert.assertTrue(a.equals(b));
        Assert.assertTrue(b.equals(a));
    }
