    public String getNameOfJspClass (String jsp)
    {
        if (StringUtil.isBlank(jsp))
            return ""; //empty

        jsp = jsp.trim();
        if ("/".equals(jsp))
            return ""; //only slash

        int i = jsp.lastIndexOf('/');
        if (i == jsp.length()-1)
            return ""; //ends with slash

        jsp = jsp.substring(i+1);
        try
        {
            Class<?> jspUtil = Loader.loadClass("org.apache.jasper.compiler.JspUtil");
            Method makeJavaIdentifier = jspUtil.getMethod("makeJavaIdentifier", String.class);
            return (String)makeJavaIdentifier.invoke(null, jsp);
        }
        catch (Exception e)
        {
            String tmp = jsp.replace('.','_');
            if (LOG.isDebugEnabled())
            {
                LOG.warn("JspUtil.makeJavaIdentifier failed for jsp "+jsp +" using "+tmp+" instead");
                LOG.warn(e);
            }
            return tmp;
        }
    }
