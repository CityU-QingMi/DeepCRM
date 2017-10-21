        public void generate() throws IOException {
            Map<String, Object> params = context.getParameters();

            Attributes attrs = new Attributes();

            attrs.addIfExists("name", params.get("name"))
                    .addIfExists("id", params.get("id"))
                    .addIfExists("class", params.get("cssClass"))
                    .addIfExists("style", params.get("cssStyle"))
                    .addIfExists("href", params.get("href"), false)
                    .addIfExists("title", params.get("title"))
                    .addIfExists("tabindex", params.get("tabindex"));
            start("a", attrs);
            String body = (String) params.get("body");
            if (StringUtils.isNotEmpty(body))
                characters(body, false);
            end("a");
        }
