    @Override
    protected void setUpStack() {
        super.setUpStack();
        bean1 = new Bean();
        bean1.setIntField(1);
        bean1.setStringField("val");


        expectFind("'key0'", String.class, "key0");
        expectFind("'key1'", String.class, "key1");
        expectFind("'val'", String.class, "val");
        expectFind("'val1'", String.class, "val1");
        expectFind("'1'", "1");
        expectFind("list", Arrays.asList(bean1));
        expectFind("key", "key0");
        expectFind("value", "val");
        expectFind("#{'key0' : 'val'}", new HashMap() {
            {
                put("key0", "val");
            }
        });

        expectFind("intField", 1);
        expectFind("stringField", "val");
    }
