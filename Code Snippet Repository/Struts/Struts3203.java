    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes attrs = new Attributes();

        attrs.addIfExists("name", params.get("name"))
                .addIfExists("id", params.get("id"))
                .addIfExists("onsubmit", params.get("onsubmit"))
                .addIfExists("onreset", params.get("onreset"))
                .addIfExists("action", params.get("action"))
                .addIfExists("target", params.get("target"))
                .addIfExists("enctype", params.get("enctype"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"))
                .addIfExists("title", params.get("title"))
                .addIfExists("accept-charset", params.get("acceptcharset"));
        attrs.add("method", params.containsKey("method") ? (String) params.get("method") : "post");
        start("form", attrs);
    }
