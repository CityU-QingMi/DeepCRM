    public static List<ExtensionConfig> parseList(String... rawSecWebSocketExtensions)
    {
        List<ExtensionConfig> configs = new ArrayList<>();

        for (String rawValue : rawSecWebSocketExtensions)
        {
            Iterator<String> extTokenIter = QuoteUtil.splitAt(rawValue,",");
            while (extTokenIter.hasNext())
            {
                String extToken = extTokenIter.next();
                configs.add(ExtensionConfig.parse(extToken));
            }
        }

        return configs;
    }
