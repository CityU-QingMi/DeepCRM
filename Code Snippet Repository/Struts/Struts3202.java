    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes a = new Attributes();

        a.addDefaultToEmpty("name", params.get("name"))
                .add("type", "file")
                .addIfExists("size", params.get("size"))
                .addIfExists("value", params.get("nameValue"))
                .addIfTrue("disabled", params.get("disabled"))
                .addIfExists("accept", params.get("accept"))
                .addIfExists("tabindex", params.get("tabindex"))
                .addIfExists("id", params.get("id"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"))
                .addIfExists("title", params.get("title"));
        super.start("input", a);
        super.end("input");
    }
