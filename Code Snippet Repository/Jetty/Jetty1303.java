    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof HttpFields))
            return false;

        HttpFields that = (HttpFields)o;

        // Order is not important, so we cannot rely on List.equals().
        if (size() != that.size())
            return false;

        loop: for (HttpField fi : this)
        {
            for (HttpField fa : that)
            {
                if (fi.equals(fa))
                    continue loop;
            }
            return false;
        }
        return true;
    }
