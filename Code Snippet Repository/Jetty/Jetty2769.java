    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        if (_loader==null)
            out.append("No ClassLoader\n");
        else if (_loader instanceof Dumpable)
        {
            ContainerLifeCycle.dump(out,indent,Collections.singleton(_loader));
        }
        else if (_loader instanceof URLClassLoader)
        {
            out.append(String.valueOf(_loader)).append("\n");
            ClassLoader parent = _loader.getParent();
            if (parent==null)
                ContainerLifeCycle.dump(out,indent,TypeUtil.asList(((URLClassLoader)_loader).getURLs()));
            else if (parent == Server.class.getClassLoader())
                ContainerLifeCycle.dump(out,indent,TypeUtil.asList(((URLClassLoader)_loader).getURLs()),Collections.singleton(parent.toString()));
            else if (parent instanceof Dumpable)
                ContainerLifeCycle.dump(out,indent,TypeUtil.asList(((URLClassLoader)_loader).getURLs()),Collections.singleton(parent));
            else
                ContainerLifeCycle.dump(out,indent,TypeUtil.asList(((URLClassLoader)_loader).getURLs()),Collections.singleton(new ClassLoaderDump(parent)));
        }
        else
        {
            out.append(String.valueOf(_loader)).append("\n");
            ClassLoader parent = _loader.getParent();
            if (parent==Server.class.getClassLoader())
                ContainerLifeCycle.dump(out,indent,Collections.singleton(parent.toString()));
            else if (parent instanceof Dumpable)
                ContainerLifeCycle.dump(out,indent,Collections.singleton(parent));
            else if (parent!=null)
                ContainerLifeCycle.dump(out,indent,Collections.singleton(new ClassLoaderDump(parent)));
        }
    }
