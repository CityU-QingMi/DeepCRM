    public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Object errorsObj = findValue(getListExpression());

        if (errorsObj != null) {
            Iterator itt = MakeIterator.convert(errorsObj);
            if (itt.hasNext()) {
                boolean escape = BooleanUtils.toBooleanDefaultIfNull((Boolean) params.get("escape"), false);
                Attributes attrs = new Attributes();
                attrs.addIfExists("style", params.get("cssStyle"))
                        .add("class", params.containsKey("cssClass") ? (String) params.get("cssClass") : getDefaultClass());
                start("ul", attrs);
                while (itt.hasNext()) {
                    String error = (String) itt.next();

                    //li for each error
                    start("li", null);

                    //span for error
                    start("span", null);
                    characters(error, escape);
                    end("span");
                    end("li");

                }
                end("ul");
            }
        }
    }
