    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes a = new Attributes();

        a.addDefaultToEmpty("name", params.get("name"))
                .add("type", "hidden")
                .addIfExists("value", params.get("nameValue"))
                .addIfTrue("disabled", params.get("disabled"))
                .addIfExists("id", params.get("id"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"));
        super.start("input", a);
        super.end("input");
    }
