        public Set<String> setInitParameters(Map<String, String> initParameters)
        {
            illegalStateIfContextStarted();
            Set<String> clash=null;
            for (Map.Entry<String, String> entry : initParameters.entrySet())
            {
                if (entry.getKey() == null) {
                    throw new IllegalArgumentException("init parameter name required");
                }
                if (entry.getValue() == null) {
                    throw new IllegalArgumentException("non-null value required for init parameter " + entry.getKey());
                }
                if (Holder.this.getInitParameter(entry.getKey())!=null)
                {
                    if (clash==null)
                        clash=new HashSet<String>();
                    clash.add(entry.getKey());
                }
            }
            if (clash!=null)
                return clash;
            Holder.this.getInitParameters().putAll(initParameters);
            return Collections.emptySet();
        }
