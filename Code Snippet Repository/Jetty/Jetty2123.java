        protected WebAppContext newWebApp ()
        {
            WebAppContext webApp = new WebAppContext();
            webApp.setAttribute(OSGiWebappConstants.WATERMARK, OSGiWebappConstants.WATERMARK);

            //make sure we protect also the osgi dirs specified by OSGi Enterprise spec
            String[] targets = webApp.getProtectedTargets();
            String[] updatedTargets = null;
            if (targets != null)
            {
                updatedTargets = new String[targets.length+OSGiWebappConstants.DEFAULT_PROTECTED_OSGI_TARGETS.length];
                System.arraycopy(targets, 0, updatedTargets, 0, targets.length);
            }
            else
                updatedTargets = new String[OSGiWebappConstants.DEFAULT_PROTECTED_OSGI_TARGETS.length];
            System.arraycopy(OSGiWebappConstants.DEFAULT_PROTECTED_OSGI_TARGETS, 0, updatedTargets, targets.length, OSGiWebappConstants.DEFAULT_PROTECTED_OSGI_TARGETS.length);
            webApp.setProtectedTargets(updatedTargets);

           return webApp;
        }
