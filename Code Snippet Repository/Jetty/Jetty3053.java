        @Override
        public boolean check(String path, Resource resource)
        {
            if (resource.exists())
                return false;

            String a=resource.getAlias().toString();
            String r=resource.getURI().toString();

            if (a.length()>r.length())
                return a.startsWith(r) && a.length()==r.length()+1 && a.endsWith("/");
            if (a.length()<r.length())
                return r.startsWith(a) && r.length()==a.length()+1 && r.endsWith("/");

            return a.equals(r);
        }
