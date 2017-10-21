        private Object systemPropertyObj(XmlParser.Node node) throws Exception
        {
            AttrOrElementNode aoeNode=new AttrOrElementNode(node,"Id","Name","Deprecated","Default");
            String id = aoeNode.getString("Id");
            String name = aoeNode.getString("Name",true);
            List<Object> deprecated = aoeNode.getList("Deprecated");
            String dftValue = aoeNode.getString("Default");

            // Look for a value
            String value = System.getProperty(name);
            
            // Look for a deprecated name value
            String alternate=null;
            if (!deprecated.isEmpty())
            {
                for (Object d : deprecated)
                { 
                    String v = System.getProperty(StringUtil.valueOf(d));
                    if (v!=null)
                    {
                        if (value==null)
                            LOG.warn("SystemProperty '{}' is deprecated, use '{}' instead", d, name);
                        else
                            LOG.warn("SystemProperty '{}' is deprecated, value from '{}' used", d, name);
                    }
                    if (alternate==null)
                        alternate=v;;
                }
            }

            // use alternate from deprecated
            if (value==null)
                value=alternate;
            
            // use default value
            if (value==null)
                value=dftValue;

            // Set value if ID set
            if (id != null)
                _configuration.getIdMap().put(id, value);

            return value;
        }
