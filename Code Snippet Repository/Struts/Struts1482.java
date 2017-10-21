    public void testTranslateVariablesCollection() {
        ValueStack stack = ActionContext.getContext().getValueStack();
        final List<String> list = new ArrayList<String>() {{
            add("val 1");
            add("val 2");
        }};
        stack.push(new HashMap<String, Object>() {{ put("list", list); }});

        Collection<String> collection = TextParseUtil.translateVariablesCollection("${list}", stack, true, null);

        Assert.assertNotNull(collection);
        Assert.assertEquals(2, collection.size());
    }
