    public void testEncoding() throws Exception {
        // given
        JSONResult json = new JSONResult();
        json.setEncoding("UTF-8");
        json.setDefaultEncoding("UTF-8");

        // when
        String encoding = json.getEncoding();

        // thn
        assertEquals("UTF-8", encoding);
    }
