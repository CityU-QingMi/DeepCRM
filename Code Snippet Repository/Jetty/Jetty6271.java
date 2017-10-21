        @OnMessage
        public String echo(Session session, String message)
        {
            List<Extension> negotiatedExtensions = session.getNegotiatedExtensions();
            if (negotiatedExtensions == null)
            {
                return "negotiatedExtensions=null";
            }
            else
            {
                return "negotiatedExtensions=" + negotiatedExtensions.stream()
                        .map((ext) -> ext.getName())
                        .collect(Collectors.joining(",", "[", "]"));
            }
        }
