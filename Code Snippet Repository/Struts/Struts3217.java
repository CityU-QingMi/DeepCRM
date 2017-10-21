    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes attrs = new Attributes();

        //first input
        attrs.add("type", "hidden")
                .addDefaultToEmpty("name", params.get("tokenNameField"), false)
                .addDefaultToEmpty("value", params.get("name"));

        start("input", attrs);
        end("input");

        //second input
        attrs = new Attributes();
        attrs.add("type", "hidden")
                .addDefaultToEmpty("name", params.get("name"), false)
                .addDefaultToEmpty("value", params.get("token"));

        start("input", attrs);
        end("input");
    }
