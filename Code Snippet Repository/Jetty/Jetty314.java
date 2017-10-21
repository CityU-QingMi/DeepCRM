    private String buildQuery()
    {
        StringBuilder result = new StringBuilder();
        for (Iterator<Fields.Field> iterator = params.iterator(); iterator.hasNext(); )
        {
            Fields.Field field = iterator.next();
            List<String> values = field.getValues();
            for (int i = 0; i < values.size(); ++i)
            {
                if (i > 0)
                    result.append("&");
                result.append(field.getName()).append("=");
                result.append(urlEncode(values.get(i)));
            }
            if (iterator.hasNext())
                result.append("&");
        }
        return result.toString();
    }
