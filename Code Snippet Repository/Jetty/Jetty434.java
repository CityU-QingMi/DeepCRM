    public static String convert(Fields fields, Charset charset)
    {
        // Assume 32 chars between name and value.
        StringBuilder builder = new StringBuilder(fields.getSize() * 32);
        for (Fields.Field field : fields)
        {
            for (String value : field.getValues())
            {
                if (builder.length() > 0)
                    builder.append("&");
                builder.append(encode(field.getName(), charset)).append("=").append(encode(value, charset));
            }
        }
        return builder.toString();
    }
