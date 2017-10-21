    public void testFormTagWithDifferentActionExtension() throws Exception {
        initDispatcher(new HashMap<String,String>(){{ 
            put(StrutsConstants.STRUTS_ACTION_EXTENSION, "jspa");
            put("configProviders", TestConfigurationProvider.class.getName());
        }});
        createMocks();
        request.setupGetServletPath("/testNamespace/testNamespaceAction");

        FormTag tag = new FormTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("/testNamespace");
        tag.setAction("testNamespaceAction");
        tag.setMethod("post");
        tag.setName("myForm");

        tag.doStartTag();
        tag.doEndTag();

        verify(FormTag.class.getResource("Formtag-5.txt"));
    }
