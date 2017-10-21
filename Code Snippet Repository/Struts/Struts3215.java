    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes attrs = new Attributes();

        attrs.addDefaultToEmpty("name", params.get("name"))
                .addDefaultToEmpty("cols", params.get("cols"))
                .addDefaultToEmpty("rows", params.get("rows"))
                .addIfExists("wrap", params.get("wrap"))
                .addIfTrue("disabled", params.get("disabled"))
                .addIfTrue("readonly", params.get("readonly"))
                .addIfExists("tabindex", params.get("tabindex"))
                .addIfExists("id", params.get("id"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"))
                .addIfExists("title", params.get("title"));
        start("textarea", attrs);
        String value = (String) params.get("nameValue");
        if (StringUtils.isNotEmpty(value))
            characters(value);
        end("textarea");
    }
