    public void testIteratorWithNulls() throws Exception {
        // given
        final ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(new FooAction() {
            private List items  = Arrays.asList("1", "2", null, "4");

            public List getItems() {
                return items;
            }
        });

        StringWriter out = new StringWriter();

        IteratorComponent ic = new IteratorComponent(stack);
        ic.setValue("items");
        ic.setVar("val");
        Property prop = new Property(stack);

        ic.getComponentStack().push(prop);
        ic.getComponentStack().push(prop);
        ic.getComponentStack().push(prop);
        ic.getComponentStack().push(prop);

        String body = ", ";

        // when
        assertTrue(ic.start(out));

        for (int i = 0; i < 4; i++) {
            prop.start(out);
            prop.end(out, body);
            ic.end(out, null);
        }

        // then
        assertEquals("1, 2, , 4, ", out.getBuffer().toString());
    }
