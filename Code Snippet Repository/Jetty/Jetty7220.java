        private Object itemValue(Object obj, Object item) throws Exception
        {
            // String value
            if (item instanceof String)
                return item;

            XmlParser.Node node = (XmlParser.Node)item;
            String tag = node.getTag();
            if ("Call".equals(tag))
                return call(obj,node);
            if ("Get".equals(tag))
                return get(obj,node);
            if ("New".equals(tag))
                return newObj(obj,node);
            if ("Ref".equals(tag))
                return refObj(obj,node);
            if ("Array".equals(tag))
                return newArray(obj,node);
            if ("Map".equals(tag))
                return newMap(obj,node);
            if ("Property".equals(tag))
                return propertyObj(node);
            if ("SystemProperty".equals(tag))
                return systemPropertyObj(node);
            if ("Env".equals(tag))
                return envObj(node);

            LOG.warn("Unknown value tag: " + node,new Throwable());
            return null;
        }
