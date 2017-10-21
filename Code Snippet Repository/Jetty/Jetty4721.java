    public void appendJSON(final Appendable buffer, final Convertor convertor, final Object object)
    {
        appendJSON(buffer,new Convertible()
        {
            public void fromJSON(Map object)
            {
            }

            public void toJSON(Output out)
            {
                convertor.toJSON(object,out);
            }
        });
    }
