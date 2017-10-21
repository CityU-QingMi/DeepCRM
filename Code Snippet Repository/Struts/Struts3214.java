        public void generate() throws IOException {
            Map<String, Object> params = context.getParameters();
            String body = (String) params.get("body");

            String type = StringUtils.defaultString((String) params.get("type"), "input");
            if ("button".equals(type)) {
                //button body
                if (StringUtils.isNotEmpty(body))
                    characters(body, false);
                else if (params.containsKey("label")) {
                    String label = (String) params.get("label");
                    if (StringUtils.isNotEmpty(label))
                        characters(label, false);
                }
                end("button");
            } else if ("image".equals(type)) {
                if (StringUtils.isNotEmpty(body))
                    characters(body, false);
                end("input");
            } else
                end("input");
        }
