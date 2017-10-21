            public Object run()
            {
/**/
/**/
/**/
/**/
/**/
                loadProperties("jetty-logging.properties",__props);

/**/
/**/
/**/
/**/
                String osName = System.getProperty("os.name");
                // NOTE: cannot use jetty-util's StringUtil as that initializes logging itself.
                if (osName != null && osName.length() > 0)
                {
                    osName = osName.toLowerCase(Locale.ENGLISH).replace(' ','-');
                    loadProperties("jetty-logging-" + osName + ".properties",__props);
                }

/**/
/**/
/**/
                @SuppressWarnings("unchecked")
                Enumeration<String> systemKeyEnum = (Enumeration<String>)System.getProperties().propertyNames();
                while (systemKeyEnum.hasMoreElements())
                {
                    String key = systemKeyEnum.nextElement();
                    String val = System.getProperty(key);
                    // protect against application code insertion of non-String values (returned as null)
                    if (val != null)
                    {
                        __props.setProperty(key,val);
                    }
                }

/**/
/**/
                __logClass = __props.getProperty("org.eclipse.jetty.util.log.class","org.eclipse.jetty.util.log.Slf4jLog");
                __ignored = Boolean.parseBoolean(__props.getProperty("org.eclipse.jetty.util.log.IGNORED","false"));
                return null;
            }
