    public void testTranslateVariablesCollectionWithExpressions() {
        ValueStack stack = ActionContext.getContext().getValueStack();
        final List<String> list = new ArrayList<String>() {{
            add("${val1}");
            add("%{val2}");
        }};
        stack.push(new HashMap<String, Object>() {{ put("list", list); put("val1", 1); put("val2", "Value 2"); }});

        Collection<String> collection = TextParseUtil.translateVariablesCollection("${list}", stack, true, null);

        Assert.assertNotNull(collection);
        Assert.assertEquals(2, collection.size());

        // if this starts passing, probably an double evaluation expression vulnerability was introduced
        // carefully review changes as this can affect users and allows break in intruders
        Assert.assertEquals("${val1}", collection.toArray()[0]);
        Assert.assertEquals("%{val2}", collection.toArray()[1]);
    }
