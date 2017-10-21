     protected void startClient(String user, String pwd)
         throws Exception
     {
         _client = new HttpClient();
         QueuedThreadPool executor = new QueuedThreadPool();
         executor.setName(executor.getName() + "-client");
         _client.setExecutor(executor);
         AuthenticationStore authStore = _client.getAuthenticationStore();
         authStore.addAuthentication(new BasicAuthentication(_baseUri, __realm, user, pwd));
         _client.start();
     }
