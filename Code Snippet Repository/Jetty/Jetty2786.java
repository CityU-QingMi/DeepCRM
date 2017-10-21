        @Override
        public void enterScope(Context context, Request request, Object reason)
        {
            String cname=findContextName(context);
            if (request==null)
                log(">  ctx=%s %s",cname,reason);
            else
            {
                String rname=findRequestName(request);

                if (_renameThread)
                {
                    Thread thread=Thread.currentThread();
                    thread.setName(String.format("%s#%s",thread.getName(),rname));
                }
            
                log(">  ctx=%s r=%s %s",cname,rname,reason);
            }
        }
