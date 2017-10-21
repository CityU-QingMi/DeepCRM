    public static List<ExtensionConfig> parseEnum(Enumeration<String> valuesEnum)
    {
        List<ExtensionConfig> configs = new ArrayList<>();

        if (valuesEnum != null)
        {
            while (valuesEnum.hasMoreElements())
            {
                Iterator<String> extTokenIter = QuoteUtil.splitAt(valuesEnum.nextElement(),",");
                while (extTokenIter.hasNext())
                {
                    String extToken = extTokenIter.next();
                    configs.add(ExtensionConfig.parse(extToken));
                }
            }
        }

        return configs;
    }
