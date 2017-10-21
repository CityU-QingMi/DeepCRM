    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        dumpBeans(out,indent,
                Collections.singletonList(new ClassLoaderDump(getClassLoader())),
                Collections.singletonList(new DumpableCollection("Handler attributes "+this,((AttributesMap)getAttributes()).getAttributeEntrySet())),
                Collections.singletonList(new DumpableCollection("Context attributes "+this,((Context)getServletContext()).getAttributeEntrySet())),
                Collections.singletonList(new DumpableCollection("Initparams "+this,getInitParams().entrySet()))
                );
    }
