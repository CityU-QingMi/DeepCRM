        private File getFile (String file, File bundleInstall)
        {
            if (file == null)
                return null;

            if (file.startsWith("/") || file.startsWith("file:/")) //absolute location
                return new File(file);
            else
            {
                //relative location
                //try inside the bundle first
                File f = new File (bundleInstall, file);
                if (f.exists()) return f;
                String jettyHome = (String)getDeploymentManager().getServer().getAttribute(OSGiServerConstants.JETTY_HOME);
                if (jettyHome != null)
                    return new File(jettyHome, file);
            }
            
            return null;
        }
