        private Object refObj(Object obj, XmlParser.Node node) throws Exception
        {
            String refid = node.getAttribute("refid");
            if (refid==null)
                refid = node.getAttribute("id");
            obj = _configuration.getIdMap().get(refid);
            if (obj == null && node.size()>0)
                throw new IllegalStateException("No object for refid=" + refid);
            configure(obj,node,0);
            return obj;
        }
