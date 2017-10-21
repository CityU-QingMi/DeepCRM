    @SuppressWarnings("")
    @Override
    public <T extends Handler> T getChildHandlerByClass(Class<T> byclass)
    {
        List<Handler> list=new ArrayList<>();
        expandChildren(list,byclass);
        if (list.isEmpty())
            return null;
        return (T)list.get(0);
    }
