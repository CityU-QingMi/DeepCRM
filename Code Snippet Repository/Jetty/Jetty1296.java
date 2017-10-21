    public Enumeration<String> getValues(final String name)
    {
        for (int i=0;i<_size;i++)
        {
            final HttpField f = _fields[i];
            
            if (f.getName().equalsIgnoreCase(name) && f.getValue()!=null)
            {
                final int first=i;
                return new Enumeration<String>()
                {
                    HttpField field=f;
                    int i = first+1;

                    @Override
                    public boolean hasMoreElements()
                    {
                        if (field==null)
                        {
                            while (i<_size) 
                            {
                                field=_fields[i++];
                                if (field.getName().equalsIgnoreCase(name) && field.getValue()!=null)
                                    return true;
                            }
                            field=null;
                            return false;
                        }
                        return true;
                    }

                    @Override
                    public String nextElement() throws NoSuchElementException
                    {
                        if (hasMoreElements())
                        {
                            String value=field.getValue();
                            field=null;
                            return value;
                        }
                        throw new NoSuchElementException();
                    }
                };
            }
        }

        List<String> empty=Collections.emptyList();
        return Collections.enumeration(empty);
    }
