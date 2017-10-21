    private void updatePattern()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("^(");
        for (String pattern: _patterns)
        {
            if (builder.length()>2)
                builder.append('|');
            builder.append('(');
            builder.append(pattern);
            builder.append(')');
        }
        builder.append(")$");
        _pattern = Pattern.compile(builder.toString());   
    }
