        public static ClassList setServerDefault(Server server)
        {
            ClassList cl=server.getBean(ClassList.class);
            if (cl!=null)
                return cl;
            cl=serverDefault(server);
            server.addBean(cl);
            server.setAttribute(ATTR,null);
            return cl;
        }
