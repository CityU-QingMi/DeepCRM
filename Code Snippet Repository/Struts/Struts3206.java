    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes a = new Attributes();

        a.addDefaultToEmpty("name", params.get("name"))
                .addIfExists("for", params.get("for"))
                .addIfExists("id", params.get("id"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"))
                .addIfExists("title", params.get("title"));
        super.start("label", a);
        String value = (String) params.get("nameValue");
        if (StringUtils.isNotEmpty(value))
            characters(value);
        super.end("label");
    }
