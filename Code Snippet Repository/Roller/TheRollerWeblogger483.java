    private static ParsedMenu unmarshall(String menuId, InputStream instream)
            throws IOException, JDOMException {

        if (instream == null) {
            throw new IOException("InputStream is null!");
        }

        ParsedMenu config = new ParsedMenu();

        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(instream);

        Element root = doc.getRootElement();
        List<Element> parsedMenus = root.getChildren("menu");
        for (Element e : parsedMenus) {
            config.addTab(elementToParsedTab(menuId, e));
        }

        return config;
    }
