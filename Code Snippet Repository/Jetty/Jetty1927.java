        @Override
        protected TldScanner newTldScanner(JspCServletContext context, boolean namespaceAware, boolean validate, boolean blockExternal)
        {            
            if (context != null && context.getAttribute(JarScanner.class.getName()) == null) 
            {
                StandardJarScanner jarScanner = new StandardJarScanner();             
                jarScanner.setScanAllDirectories(getScanAllDirectories());
                context.setAttribute(JarScanner.class.getName(), jarScanner);
            }
                
            return super.newTldScanner(context, namespaceAware, validate, blockExternal);
        }      
