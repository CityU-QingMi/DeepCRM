    public Collection<Resource> getAllResources()
    {
        try
        {
            ArrayList<Resource> deep=new ArrayList<>();
            {
                String[] list=list();
                if (list!=null)
                {
                    for (String i:list)
                    {
                        Resource r=addPath(i);
                        if (r.isDirectory())
                            deep.addAll(r.getAllResources());
                        else
                            deep.add(r);
                    }
                }
            }
            return deep;
        }
        catch(Exception e)
        {
            throw new IllegalStateException(e);
        }
    }
