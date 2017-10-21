    @Override
    public void start(String name, Attributes a) throws IOException {
        Map params = context.getParameters();
        a.addIfExists("onclick", params.get("onclick"));
        a.addIfExists("ondblclick", params.get("ondblclick"));
        a.addIfExists("onmousedown", params.get("onmousedown"));
        a.addIfExists("onmouseup", params.get("onmouseup"));
        a.addIfExists("onmouseover", params.get("onmouseover"));
        a.addIfExists("onmousemove", params.get("onmousemove"));
        a.addIfExists("onmouseout", params.get("onmouseout"));
        a.addIfExists("onfocus", params.get("onfocus"));
        a.addIfExists("onblur", params.get("onblur"));
        a.addIfExists("onkeypress", params.get("onkeypress"));
        a.addIfExists("onkeydown", params.get("onkeydown"));
        a.addIfExists("onkeyup", params.get("onkeyup"));
        a.addIfExists("onselect", params.get("onselect"));
        a.addIfExists("onchange", params.get("onchange"));

        super.start(name, a);
    }
