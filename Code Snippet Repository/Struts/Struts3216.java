    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes attr = new Attributes();
        Object type = params.get("type");
        attr.add("type", type == null ? "text" : type.toString())
                .addDefaultToEmpty("name", params.get("name"))
                .addIfExists("size", params.get("size"))
                .addIfExists("maxlength", params.get("maxlength"))
                .addIfExists("value", params.get("nameValue"))
                .addIfTrue("disabled", params.get("disabled"))
                .addIfTrue("readonly", params.get("readonly"))
                .addIfExists("tabindex", params.get("tabindex"))
                .addIfExists("id", params.get("id"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"))
                .addIfExists("title", params.get("title"));
        super.start("input", attr);
        super.end("input");
    }
