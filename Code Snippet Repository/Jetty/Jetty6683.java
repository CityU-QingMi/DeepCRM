    public Utf8PartialBuilder()
    {
        this.str = new StringBuilder();
        this.utf8 = new Utf8Appendable(str)
        {
            @Override
            public int length()
            {
                return str.length();
            }
        };
    }
