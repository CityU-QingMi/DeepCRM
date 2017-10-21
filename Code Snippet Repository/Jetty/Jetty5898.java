        public static ClassList serverDefault(Server server)
        {
            ClassList cl=null;
            if (server!=null)
            {
                cl= server.getBean(ClassList.class);
                if (cl!=null)
                    return new ClassList(cl);
                Object attr = server.getAttribute(ATTR);
                if (attr instanceof ClassList)
                    return new ClassList((ClassList)attr);
                if (attr instanceof String[])
                    return new ClassList((String[])attr);
            }
            return new ClassList();
        }
