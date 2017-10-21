        @Override
        public Object addingBundle(Bundle bundle, BundleEvent event)
        {
            try
            {
                String serverName = (String)bundle.getHeaders().get(OSGiServerConstants.MANAGED_JETTY_SERVER_NAME);
                if ((StringUtil.isBlank(serverName) && _managedServerName.equals(OSGiServerConstants.MANAGED_JETTY_SERVER_DEFAULT_NAME))
                     || (!StringUtil.isBlank(serverName) && (serverName.equals(_managedServerName))))
                {
                    if (bundleAdded (bundle))
                        return bundle;
                }
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
            return null;
        }
