        public Object getObjectInstance(Object obj, Name name, Context ctx, Hashtable env) throws Exception
        {

            if (!env.containsKey("flavour"))
                throw new Exception ("No flavour!");

            if (obj instanceof Reference)
            {
                Reference ref = (Reference)obj;
                if (ref.getClassName().equals(Fruit.class.getName()))
                {
                    RefAddr addr = ref.get("fruit");
                    if (addr != null)
                    {
                        return new Fruit((String)addr.getContent());
                    }
                }
            }
            return null;
         }
