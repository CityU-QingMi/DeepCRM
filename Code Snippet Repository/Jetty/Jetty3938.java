    public String getPackageOfJspClass (String jsp)
    {
        if (jsp == null)
            return "";

        int i = jsp.lastIndexOf('/');
        if (i <= 0)
            return "";
        try
        {
            Class<?> jspUtil = Loader.loadClass("org.apache.jasper.compiler.JspUtil");
            Method makeJavaPackage = jspUtil.getMethod("makeJavaPackage", String.class);
            String p = (String)makeJavaPackage.invoke(null, jsp.substring(0,i));
            return p;
        }
        catch (Exception e)
        {
            String tmp = jsp;
            
            //remove any leading slash
            int s = 0;
            if ('/' == (tmp.charAt(0)))
                s = 1;
            
            //remove the element after last slash, which should be name of jsp
            tmp = tmp.substring(s,i);

            tmp = tmp.replace('/','.').trim();
            tmp = (".".equals(tmp)? "": tmp);
            if (LOG.isDebugEnabled())
            {
                LOG.warn("JspUtil.makeJavaPackage failed for "+jsp +" using "+tmp+" instead");
                LOG.warn(e);
            }
            return tmp;
        }
    }
