    public void testFormattingSupportWithConversionError() throws Exception {
        ActionContext.getContext().getConversionErrors().put("val", new String[]{"4567def"});
        ActionContext.getContext().setLocale(new Locale("da"));
        MyActionSupport mas = new MyActionSupport();
        container.inject(mas);
        ActionContext.getContext().getValueStack().push(mas);

        mas.setVal(234d);

        String formatted = mas.getFormatted("format.number", "val");

        assertEquals("4567def", formatted);
    }
