    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes attrs = new Attributes();

        String type = StringUtils.defaultString((String) params.get("type"), "input");

        if ("button".equals(type)) {
            attrs.addIfExists("name", params.get("name"))
                    .add("type", "submit")
                    .addIfExists("value", params.get("nameValue"))
                    .addIfTrue("disabled", params.get("disabled"))
                    .addIfExists("tabindex", params.get("tabindex"))
                    .addIfExists("id", params.get("id"))
                    .addIfExists("class", params.get("cssClass"))
                    .addIfExists("style", params.get("cssStyle"));

            start("button", attrs);
        } else if ("image".equals(type)) {
            attrs.addIfExists("src", params.get("src"))
                    .add("type", "image")
                    .addIfExists("name", params.get("name"))
                    .addIfExists("alt", params.get("label"))
                    .addIfExists("id", params.get("id"))
                    .addIfExists("class", params.get("cssClass"))
                    .addIfExists("style", params.get("cssStyle"))
                    .addIfExists("title", params.get("title"));

            start("input", attrs);
        } else {
            attrs.addIfExists("name", params.get("name"))
                    .add("type", "submit")
                    .addIfExists("value", params.get("nameValue"))
                    .addIfTrue("disabled", params.get("disabled"))
                    .addIfExists("tabindex", params.get("tabindex"))
                    .addIfExists("id", params.get("id"))
                    .addIfExists("class", params.get("cssClass"))
                    .addIfExists("style", params.get("cssStyle"));

            start("input", attrs);
        }
    }
