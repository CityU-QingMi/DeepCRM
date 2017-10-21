    public void add(String name, String value)
    {
        String key = normalizeName(name);
        Field field = fields.get(key);
        if (field == null)
        {
            // Preserve the case for the field name
            field = new Field(name, value);
            fields.put(key, field);
        }
        else
        {
            field = new Field(field.getName(), field.getValues(), value);
            fields.put(key, field);
        }
    }
