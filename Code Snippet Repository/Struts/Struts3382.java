    @Test
    public void testWriteBeanWithList() throws Exception {
        BeanWithList bean1 = new BeanWithList();
        bean1.setStringField("str");
        bean1.setBooleanField(true);
        bean1.setCharField('s');
        bean1.setDoubleField(10.1);
        bean1.setFloatField(1.5f);
        bean1.setIntField(10);
        bean1.setLongField(100);
        bean1.setEnumField(AnEnum.ValueA);
        bean1.setEnumBean(AnEnumBean.Two);
        List<String> errors = new ArrayList<String>();
        errors.add("Field is required");
        bean1.setErrors(errors);

        JSONWriter jsonWriter = new JSONWriter();
        jsonWriter.setEnumAsBean(false);
        jsonWriter.setIgnoreHierarchy(false);
        String json = jsonWriter.write(bean1);
        TestUtils.assertEquals(JSONWriter.class.getResource("jsonwriter-write-bean-04.txt"), json);
    }
