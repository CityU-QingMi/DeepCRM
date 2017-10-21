        private TagHandlerInfo getTagHandlerInfo(Node.CustomTag n)
                throws JasperException {
            Hashtable handlerInfosByShortName = (Hashtable) handlerInfos.get(n
                    .getPrefix());
            if (handlerInfosByShortName == null) {
                handlerInfosByShortName = new Hashtable();
                handlerInfos.put(n.getPrefix(), handlerInfosByShortName);
            }
            TagHandlerInfo handlerInfo = (TagHandlerInfo) handlerInfosByShortName
                    .get(n.getLocalName());
            if (handlerInfo == null) {
                handlerInfo = new TagHandlerInfo(n, n.getTagHandlerClass(), err);
                handlerInfosByShortName.put(n.getLocalName(), handlerInfo);
            }
            return handlerInfo;
        }
