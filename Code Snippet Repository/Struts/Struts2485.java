    protected ClassLoaderInterface getClassLoaderInterface() {

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
        ClassLoaderInterface classLoaderInterface = null;
        ActionContext ctx = ActionContext.getContext();
        if (ctx != null)
            classLoaderInterface = (ClassLoaderInterface) ctx.get(ClassLoaderInterface.CLASS_LOADER_INTERFACE);

        return ObjectUtils.defaultIfNull(classLoaderInterface, new ClassLoaderInterfaceDelegate(Thread.currentThread().getContextClassLoader()));

    }
