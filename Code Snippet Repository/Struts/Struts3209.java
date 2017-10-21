    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes attrs = new Attributes();

        boolean isButton = "button".equals(params.get("type"));

        attrs.addDefaultToEmpty("name", params.get("name"))
                .add("type", "reset")
                .addIfExists("value", params.get("nameValue"))
                .addIfExists("tabindex", params.get("tabindex"))
                .addIfExists("id", params.get("id"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"));

        if (!isButton)
            attrs.addIfExists("title", params.get("title"));

        super.start("input", attrs);
        if (isButton) {
            String label = (String) params.get("label");
            if (StringUtils.isNotEmpty(label))
                characters(label);
        }

        super.end("input");
    }
