    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Fields that = (Fields)obj;
        if (getSize() != that.getSize())
            return false;
        if (caseSensitive != that.caseSensitive)
            return false;
        for (Map.Entry<String, Field> entry : fields.entrySet())
        {
            String name = entry.getKey();
            Field value = entry.getValue();
            if (!value.equals(that.get(name), caseSensitive))
                return false;
        }
        return true;
    }
