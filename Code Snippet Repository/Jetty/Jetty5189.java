    @Override
    public String[] list()
    {
        String[] list =_file.list();
        if (list==null)
            return null;
        for (int i=list.length;i-->0;)
        {
            if (new File(_file,list[i]).isDirectory() &&
                    !list[i].endsWith("/"))
                list[i]+="/";
        }
        return list;
    }
