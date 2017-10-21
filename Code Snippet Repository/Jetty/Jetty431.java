    private List<String> splitParams(String paramString)
    {
        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < paramString.length(); ++i)
        {
            int quotes = 0;
            char ch = paramString.charAt(i);
            switch (ch)
            {
                case '\\':
                    ++i;
                    break;
                case '"':
                    ++quotes;
                    break;
                case ',':
                    if (quotes % 2 == 0)
                    {
                        String element = paramString.substring(start, i).trim();
                        if (element.length() > 0)
                            result.add(element);
                        start = i + 1;
                    }
                    break;
                default:
                    break;
            }
        }
        result.add(paramString.substring(start, paramString.length()).trim());
        return result;
    }
