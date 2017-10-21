    protected Map<String, PropertyHolder> initializedGenericTagTestProperties() {
        Map<String, PropertyHolder> result = new HashMap<String, PropertyHolder>();
        new PropertyHolder("name", "someName").addToMap(result);
        new PropertyHolder("id", "someId").addToMap(result);
        new PropertyHolder("cssClass", "cssClass1", "class=\"cssClass1\"").addToMap(result);
        new PropertyHolder("cssStyle", "cssStyle1", "style=\"cssStyle1\"").addToMap(result);
        new PropertyHolder("title", "someTitle").addToMap(result);
        new PropertyHolder("disabled", "true", "disabled=\"disabled\"").addToMap(result);
        //new PropertyHolder("label", "label", "label=\"label\"").addToMap(result);
        //new PropertyHolder("required", "someTitle").addToMap(result);
        new PropertyHolder("tabindex", "99").addToMap(result);
        new PropertyHolder("value", "someValue").addToMap(result);
        new PropertyHolder("onclick", "onclick1").addToMap(result);
        new PropertyHolder("ondblclick", "ondblclick1").addToMap(result);
        new PropertyHolder("onmousedown", "onmousedown1").addToMap(result);
        new PropertyHolder("onmouseup", "onmouseup1").addToMap(result);
        new PropertyHolder("onmouseover", "onmouseover1").addToMap(result);
        new PropertyHolder("onmousemove", "onmousemove1").addToMap(result);
        new PropertyHolder("onmouseout", "onmouseout1").addToMap(result);
        new PropertyHolder("onfocus", "onfocus1").addToMap(result);
        new PropertyHolder("onblur", "onblur1").addToMap(result);
        new PropertyHolder("onkeypress", "onkeypress1").addToMap(result);
        new PropertyHolder("onkeydown", "onkeydown1").addToMap(result);
        new PropertyHolder("onkeyup", "onkeyup1").addToMap(result);
        new PropertyHolder("onclick", "onclick1").addToMap(result);
        new PropertyHolder("onselect", "onchange").addToMap(result);
        return result;
    }
