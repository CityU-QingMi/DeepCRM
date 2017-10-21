    @Parameterized.Parameters(name = "")
    public static List<Object[]> data()
    {
        ArrayList<Object[]> data = new ArrayList<>();
        
        data.add(new Object[]{ "Name=xx%zzyy", UTF_8, IllegalArgumentException.class });
        data.add(new Object[]{ "Name=%FF%FF%FF", UTF_8, Utf8Appendable.NotUtf8Exception.class });
        data.add(new Object[]{ "Name=%EF%EF%EF", UTF_8, Utf8Appendable.NotUtf8Exception.class });
        data.add(new Object[]{ "Name=%E%F%F", UTF_8, IllegalArgumentException.class });
        data.add(new Object[]{ "Name=x%", UTF_8, Utf8Appendable.NotUtf8Exception.class });
        data.add(new Object[]{ "Name=x%2", UTF_8, Utf8Appendable.NotUtf8Exception.class });
        data.add(new Object[]{ "Name=xxx%", UTF_8, Utf8Appendable.NotUtf8Exception.class });
        data.add(new Object[]{ "name=X%c0%afZ", UTF_8, Utf8Appendable.NotUtf8Exception.class });
        return data;
    }
