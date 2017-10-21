        @Override
        public void exitScope(Context context, Request request)
        {
            String cname=findContextName(context);
            if (request==null)
                log("<  ctx=%s",cname);
            else
            {
                String rname=findRequestName(request);

                log("<  ctx=%s r=%s",cname,rname);
                if (_renameThread)
                {
                    Thread thread=Thread.currentThread();
                    if (thread.getName().endsWith(rname))
                        thread.setName(thread.getName().substring(0,thread.getName().length()-rname.length()-1));
                }
            }
        }   
