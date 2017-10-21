        @Override
        public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException
        {
            AsyncContext async = request.startAsync();
            
            request.getInputStream().setReadListener(new ReadListener()
            {
                
                @Override
                public void onError(Throwable t)
                {                    
                }
                
                @Override
                public void onDataAvailable() throws IOException
                {                    
                }
                
                @Override
                public void onAllDataRead() throws IOException
                {                    
                }
            });
            
            response.setStatus(200);
            response.getOutputStream().print("DONE");
            async.complete();
        }
