    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes attrs = new Attributes();

        Boolean showPassword = (Boolean) params.get("showPassword");
        if (showPassword != null && showPassword)
           attrs.addIfExists("value",  params.get("nameValue"));

        attrs.addDefaultToEmpty("name", params.get("name"))
                .add("type", "password")
                .addIfExists("size", params.get("size"))
                .addIfExists("maxlength", params.get("maxlength"))
                .addIfTrue("disabled", params.get("disabled"))
                .addIfTrue("readonly", params.get("readonly"))
                .addIfExists("tabindex", params.get("tabindex"))
                .addIfExists("id", params.get("id"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"))
                .addIfExists("title", params.get("title"));
        super.start("input", attrs);
        super.end("input");
    }
