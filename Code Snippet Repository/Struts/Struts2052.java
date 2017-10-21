    public void testEvaluateValue() throws Exception {
        URLTagTest.Foo foo = new URLTagTest.Foo();
        foo.setTitle("test");
        stack.push(foo);
        tag.setValue("%{title}");

        tag.doStartTag();
        tag.doEndTag();
        assertEquals(wrapWithAnchor("test"), writer.toString());
    }
