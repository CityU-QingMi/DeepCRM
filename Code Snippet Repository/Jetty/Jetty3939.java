    public String getJspPackagePrefix ()
    {
        String jspPackageName = null;

        if (getServletHandler() != null && getServletHandler().getServletContext() != null)
            jspPackageName = (String)getServletHandler().getServletContext().getInitParameter(JSP_GENERATED_PACKAGE_NAME );

        if (jspPackageName == null)
            jspPackageName = "org.apache.jsp";

        return jspPackageName;
    }
